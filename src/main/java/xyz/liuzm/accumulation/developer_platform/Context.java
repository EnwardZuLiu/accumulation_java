package xyz.liuzm.accumulation.developer_platform;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import xyz.liuzm.accumulation.developer_platform.xml.DataNode;
import xyz.liuzm.accumulation.developer_platform.xml.DataNodes;
import xyz.liuzm.accumulation.json.JsonObjects;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Context {

    /**
     * 这里使用一个类变量来存放所有的策略实现类对象，这样做的主要原因是频繁的使用反射来创建对象会拖慢解析速度。
     * 将所有已存在的策略实现对象存放到一个对象池里面，这样可以大量减少垃圾回收
     */
    private static final Set<Strategy> set = Sets.newHashSet();

    /**
     * 该便来主要用来存放流程的所有数据节点
     */
    private List<DataNode> list;

    /**
     * 我们将所有的数据节点逐一解析并封装成一个特定的对象（策略模式的输入参数），统一所有输入参数
     */
    private List<OriginalData> originalDataList;

    private Map metadata = Maps.newHashMap();

    private String parameterJson;

    private Context(){}

    public Map getMetadata() {
        return metadata;
    }

    public String getParameterJson() {
        return parameterJson;
    }

    /**
     * 将 ui 和所有的固定数据存放到一个 map 集合中，map 中的值如果要对应到指定的数据节点中，需要保证
     * key 值与指定节点的 source 值保证一致。这样我们才可以将 ui 值与数据节点中得值进行合并
     *
     * @param map ui 数据以及固定数据
     * @param dataNodes xml 中得所有数据节点
     */
    private void merge(Map<String, String> map, DataNodes dataNodes) {
        this.list = dataNodes.getDataNode().stream().map(dataNode -> {
            if (Objects.nonNull(map.get(dataNode.getSource()))){
                dataNode.setValue(map.get(dataNode.getSource()));
            }
            return dataNode;
        }).collect(Collectors.toList());
    }

    /**
     *
     */
    private void assembly() {
        this.originalDataList = this.list.stream().map(dataNode -> {
            OriginalData od = OriginalData.Builder.instance().addOriginal(dataNode.getValue(), dataNode.getId())
                .addReturnType(dataNode.getParameterType())
                .addClazzType(dataNode.getClassType())
                .addHandleStrategy(dataNode.getStrategy())
                .addDestination(dataNode.getTarget())
                .build();
            return od;
        }).collect(Collectors.toList());
    }

    /**
     * 输入参数为 assembly 方法封装过的对象，该对象里面存放了`策略`、`返回类型`。根据用户提供的策略
     * 在当前 package 下找到该策略对应的实现类，如果实现类不存在该说明该策略不存在。
     * 获得该策略的class 对象之后，查看对象池中是否存在该类的对象。最后调用对象中得 handle 方法生成结果
     *
     * @param od assembly 得到的封装对象
     * @return
     */
    private ParameterValueObject build(OriginalData od) {
        Class c;
        try {
            c = Class.forName(Context.class.getPackage().getName() + "." + od.getStrategy());
        } catch (ClassNotFoundException e) {
            throw new InvalidStrategyException("该策略不存在：" + od.getStrategy());
        }
        Strategy strategy = null;
        for(Strategy s : set) {
            if (s.getClass().equals(c)) {
                strategy = s;
            }
        }
        try {
            if (Objects.isNull(strategy)) {
                strategy = (Strategy) c.newInstance();
                set.add(strategy);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new InvalidStrategyException("创建策略类的对象出错，" + e.getMessage());
        }
        ParameterValueObject p = strategy.handle(od);
        return p;
    }

    /**
     *
     */
    private void finish() {

        List<OriginalData> originalDataMetadataList = originalDataList.stream().filter(originalData -> originalData.getDestination().equals(OriginalData.Destination.METADATA)).collect(Collectors.toList());
        List<OriginalData> originalDataParameterJsonList = originalDataList.stream().filter(originalData -> originalData.getDestination().equals(OriginalData.Destination.PARAMETER_JSON)).collect(Collectors.toList());

        Map<String, ParameterValueObject> parameterValueObjectMap = Maps.newHashMap();

        originalDataParameterJsonList.stream().forEach(originalData -> {
            parameterValueObjectMap.put(originalData.getKey(),build(originalData));
        });
        this.parameterJson = JsonObjects.beanToJson(parameterValueObjectMap);

        originalDataMetadataList.stream().forEach(originalData -> {
            this.metadata.put(originalData.getKey(), originalData.getOriginal());
        });
    }

    public static Context newInstance(Map<String, String> map, DataNodes dataNodes){
        Context c = new Context();
        c.merge(map, dataNodes);
        c.assembly();
        c.finish();
        return c;
    }

}

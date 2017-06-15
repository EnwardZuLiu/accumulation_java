package xyz.liuzm.accumulation.developer_platform;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import xyz.liuzm.accumulation.json.JsonObjects;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RawDataStrategy implements Strategy {

    @Override
    public ParameterValueObject handle(OriginalData data) {
        Objects.requireNonNull(data);
        Objects.requireNonNull(data.getOriginal());
        Objects.requireNonNull(data.getKey());
        Objects.requireNonNull(data.getClazz());

        Map<String, Map<String, String>> map = JsonObjects.jsonToBean((String)data.getOriginal(), Map.class);

        ParameterValueObject.ParameterValueAndSizeObject pvad = new ParameterValueObject.ParameterValueAndSizeObject();
        pvad.setClazz(data.getClazz());

        if (data.getKey().contains("1")) {
            Map<String, String> m = getValueString("fq1", map);
            pvad.setValue(m.get("value"));
            pvad.setSize(m.get("size"));
        } else if (data.getKey().contains("2")) {
            Map<String, String> m = getValueString("fq2", map);
            pvad.setValue(m.get("value"));
            pvad.setSize(m.get("size"));
        } else {
            throw new InvalidStrategyException("原始数据的解析策略为，id（key）中必须包含字符1或者字符2，如果为单端数据" +
                "请包含字符1，双端数据则分别包含字符1和字符2");
        }
        return pvad;
    }

    private Map<String, String> getValueString(String s, Map<String, Map<String, String>> map) {
        Objects.requireNonNull(map);

        List<String> fileUUidList = Lists.newArrayList();
        List<String> fileSizeList = Lists.newArrayList();
        Iterator<Map.Entry<String, Map<String, String>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Map<String, String>> entry = it.next();
            Map<String, String> valueEntry = entry.getValue();
            String fileUuid = valueEntry.get(s + "_uuid");
            String fileSize = valueEntry.get(s + "_size");

            Preconditions.checkNotNull(fileUuid, String.format("原始数据对中，不存在`%s_uuid`这个的key，以及它对应的值为null", s));
            Preconditions.checkNotNull(fileUuid, String.format("原始数据对中，不存在`%s_size`这个的key，以及它对应的值为null", s));

            fileUUidList.add(fileUuid);
            fileSizeList.add(fileSize);
        }
        Map<String, String> returnMap = Maps.newHashMap();
        returnMap.put("value", Joiner.on(",").join(fileUUidList));
        returnMap.put("size", Joiner.on(",").join(fileSizeList));
        return returnMap;
    }
}

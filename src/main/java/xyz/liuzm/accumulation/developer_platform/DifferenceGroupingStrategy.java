package xyz.liuzm.accumulation.developer_platform;

import com.google.common.base.Joiner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liuzuming on 1/18/17.
 */
public class DifferenceGroupingStrategy implements Strategy {

    @Override
    public ParameterValueObject handle(OriginalData data) {
        String str = (String) data.getOriginal();
        String[] array = str.split(",");

        ParameterValueObject p = new ParameterValueObject();
        p.setClazz(data.getClazz());

        if (data.getKey().equalsIgnoreCase("com")) {
            List<String> list = Arrays.stream(array).filter(s -> s.split("_").length == 3).collect(Collectors.toList());
            String result = Joiner.on(",").join(list);
            p.setValue(result);
        } else if (data.getKey().equalsIgnoreCase("sep")) {
            List<String> list = Arrays.stream(array).filter(s -> s.split("_").length > 3).collect(Collectors.toList());
            String result = Joiner.on(",").join(list);
            p.setValue(result);
        } else {
            throw new InvalidStrategyException(data.getKey() + "该参数无法使用 DifferenceGrouping 策略");
        }
        return p;
    }
}

package xyz.liuzm.accumulation.developer_platform;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ParameterValueObject {

    @JsonProperty("class")
    private String clazz;
    private String value;

    @Data
    public static class ParameterValueAndSizeObject extends ParameterValueObject {
        private String size;
    }

}

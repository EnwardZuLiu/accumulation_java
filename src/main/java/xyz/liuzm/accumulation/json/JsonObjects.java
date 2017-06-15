package xyz.liuzm.accumulation.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonObjects {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T jsonToBean(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException("json to bean error," + e.getMessage());
        }
    }

    public static String beanToJson(Object bean) {
        try {
            return objectMapper.writer().writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("bean to json error," + e.getMessage());
        }
    }

}

package xyz.liuzm.accumulation.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * 这是一个 json 字符与 java bean 相互转换的工具类
 *
 * @author liuzuming
 * @version 1.0.0
 * @date 2017/6/15
 */
public class JsonObjects {

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * json to bean
     * @param json string
     * @param clazz class
     * @param <T> class type
     * @return java object
     */
    public static <T> T jsonToBean(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException ex) {
            throw new RuntimeException("json to bean error," + ex.getMessage());
        }
    }

    /**
     * java bean to json
     * @param bean java object
     * @return json string
     */
    public static String beanToJson(Object bean) {
        try {
            return OBJECT_MAPPER.writer().writeValueAsString(bean);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("bean to json error," + ex.getMessage());
        }
    }

}

package xyz.liuzm.accumulation.json;

import com.google.common.collect.Maps;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 *  json test
 */
public class JsonObjectsTest {

    private static Logger log = LoggerFactory.getLogger(JsonObjectsTest.class);

    @Test
    public void jsonToBean() throws Exception {
        String s = "{\"1\":\"1\",\"2\":\"2\",\"3\":\"3\"}";

        Map<String, String> map = JsonObjects.jsonToBean(s, Map.class);
        log.debug(map.toString());
    }

    @Test
    public void beanToJson() throws Exception {
        Map<String, String> map = Maps.newHashMap();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        String s = JsonObjects.beanToJson(map);
        log.debug(s);
    }

}
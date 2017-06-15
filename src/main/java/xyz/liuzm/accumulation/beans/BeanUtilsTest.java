package xyz.liuzm.accumulation.beans;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzuming on 3/9/17.
 */
public class BeanUtilsTest {

    @Data
    public static class NodeDO {
        private String name;
        private String email;
        private Date date;
    }

    public static class TestDO {
        private String nodeName;
        private NodeDO nodeDO;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>(){
            {
                put("nodeName", "test");
                put("nodeDO", "xiaopang|xiaopang@163.com|2015-10-20 12:00:00");
            }
        };
    }

}

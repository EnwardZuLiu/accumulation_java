package xyz.liuzm.accumulation.developer_platform;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.liuzm.accumulation.developer_platform.xml.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;


public class ContextTest {

    private static Logger log = LoggerFactory.getLogger(ContextTest.class);

    @Test
    public void newInstance() throws Exception {
        DeveloperPlatform dp = readXml();
        Map map = getUiData(dp.getWebUi());
        Context c = Context.newInstance(map, dp.getDataNodes());
        log.debug(c.getMetadata().toString());
        log.debug(c.getParameterJson());
    }

    private DeveloperPlatform readXml() throws IOException {
        URL url = Resources.getResource("ref.xml");
        String xml = Resources.toString(url, Charsets.UTF_8);
        return JaxbUtil.xmlToBean(new ByteArrayInputStream(xml.getBytes()), DeveloperPlatform.class);
    }

    private Map getUiData(WebUi webUi) {
        Map<String, String> map = Maps.newHashMap();
        List<Object> rawDataOrRadiosOrSelect = webUi.getRawDataOrRadiosOrSelect();

        for (Object o : rawDataOrRadiosOrSelect) {
            if (o instanceof RawData) {
                RawData rd = (RawData) o;
                map.put(rd.getId(), "{\"X1\":{\"fq1\":\"\",\"fq2\":\"\",\"raw_fq1\":\"Human_50A-II-X02_good_1.fq\",\"raw_fq2\":\"Human_50A-II-X02_good_2.fq\",\"fq1_uuid\":\"2ab49fc1-e788-4700-8052-67632478b7f0\",\"fq1_size\":\"31001407\",\"fq2_uuid\":\"de4b1fe1-aa46-4740-9214-0e58033d5472\",\"fq2_size\":\"31001407\"},\"X2\":{\"fq1\":\"uQ8K52n/Personal_data/exon_test_data//Human_50A-II-X01_good_1.fq\",\"fq2\":\"uQ8K52n/Personal_data/exon_test_data//Human_50A-II-X01_good_2.fq\",\"raw_fq1\":\"Human_50A-II-X01_good_1.fq\",\"raw_fq2\":\"Human_50A-II-X01_good_2.fq\",\"fq1_uuid\":\"c7884262-dcbf-4436-8635-8732308305d0\",\"fq1_size\":\"31100004\",\"fq2_uuid\":\"d14f0495-d785-4985-9e41-747850ae360d\",\"fq2_size\":\"31100004\"},\"X3\":{\"fq1\":\"uQ8K52n/Personal_data/exon_test_data//Human_50A-II-X03_good_1.fq\",\"fq2\":\"uQ8K52n/Personal_data/exon_test_data//Human_50A-II-X03_good_2.fq\",\"raw_fq1\":\"Human_50A-II-X03_good_1.fq\",\"raw_fq2\":\"Human_50A-II-X03_good_2.fq\",\"fq1_uuid\":\"bf0c8ba4-3fd8-4d6a-8043-5ae6eabd06bc\",\"fq1_size\":\"30995543\",\"fq2_uuid\":\"8c8947be-0202-48c4-aad7-8ace95ec3cf4\",\"fq2_size\":\"30995543\"},\"X4\":{\"fq1\":\"uQ8K52n/Personal_data/exon_test_data//Human_50A-II-X04_good_1.fq\",\"fq2\":\"uQ8K52n/Personal_data/exon_test_data//Human_50A-II-X04_good_2.fq\",\"raw_fq1\":\"Human_50A-II-X04_good_1.fq\",\"raw_fq2\":\"Human_50A-II-X04_good_2.fq\",\"fq1_uuid\":\"1a0f40f6-d8e6-4974-8212-497853685906\",\"fq1_size\":\"30793694\",\"fq2_uuid\":\"46e084f3-e9c3-488c-9cac-49db1f448de6\",\"fq2_size\":\"30793694\"}}");
            } else if (o instanceof Radios) {
                Radios r = (Radios) o;
                map.put(r.getId(), "0.05");
            } else if (o instanceof Select) {
                Select r = (Select) o;
                map.put(r.getId(), "0.05");
            } else if (o instanceof Input) {
                Input r = (Input) o;
                map.put(r.getId(), "0.05");
            } else if (o instanceof DifferenceGrouping) {
                DifferenceGrouping r = (DifferenceGrouping) o;
                map.put(r.getId(), "t01_vs_t02,t03_vs_t04");
            } else if (o instanceof Group) {
                Group p = (Group) o;
                List<Object> list = p.getAny();
                for (Object ob : list) {
                    if (ob instanceof Radios) {
                        Radios r = (Radios) ob;
                        map.put(r.getId(), "0.05");
                    }else if (ob instanceof Select) {
                        Select r = (Select) ob;
                        map.put(r.getId(), "0.05");
                    } else if (ob instanceof Input) {
                        Input r = (Input) ob;
                        map.put(r.getId(), "0.05");
                    } else {
                        throw new RuntimeException("解析报错," + ob.getClass().getName());
                    }
                }
            } else {
                throw new RuntimeException("解析报错," + o.getClass().getName());
            }
        }

        return map;
    }
}

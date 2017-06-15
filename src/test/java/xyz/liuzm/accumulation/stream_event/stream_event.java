package xyz.liuzm.accumulation.stream_event;


import com.codahale.metrics.MetricRegistry;
import org.junit.Test;

import java.util.UUID;

public class stream_event {

    @Test
    public void test() {
        for (int i =0;i <1000; i++) {

            EventStream es = new EventStream() {
            @Override
            public void consume(EventConsumer consumer) {
//                System.out.println(consumer.toString());
                consumer.consume(new Event(1, UUID.randomUUID()));
            }
        };


            es.consume(new ClientProjection(new ProjectionMetrics(new MetricRegistry())));
        }
    }

}

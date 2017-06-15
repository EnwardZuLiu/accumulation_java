package xyz.liuzm.accumulation.stream_event;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuzuming on 1/21/17.
 */
public class ClientProjection implements EventConsumer {

    private static ProjectionMetrics projectionMetrics;

    ClientProjection(ProjectionMetrics projectionMetrics) {
        this.projectionMetrics = projectionMetrics;
    }

    @Override
    public Event consume(Event event) {
        projectionMetrics.latency(Duration.between(event.getCreated(), Instant.now()));
        Sleeper.randSleep(10, 1);
        return event;
    }

    private static class Sleeper{
        private static final Random RANDOM = new Random();
        static void randSleep(double mean, double stdDev) {
            final double micros = 1_000 * (mean + RANDOM.nextGaussian() * stdDev);
            try {
                TimeUnit.MICROSECONDS.sleep((long) micros);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

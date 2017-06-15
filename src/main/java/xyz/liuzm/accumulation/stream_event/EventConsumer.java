package xyz.liuzm.accumulation.stream_event;

@FunctionalInterface
public interface EventConsumer {

    Event consume(Event event);

}

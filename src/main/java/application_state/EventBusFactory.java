package application_state;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executors;

public class EventBusFactory {

    private static EventBus eventBus = new AsyncEventBus(Executors.newCachedThreadPool());

    public static EventBus getEventBus() {
        return eventBus;
    }

    private static Event event = new Event();

    public static Event getEvent(){ return event; }

}
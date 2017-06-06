package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventDispatcherService;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventHandler;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class EventDispatcherServiceImpl implements EventDispatcherService {
    private Set<EventHandler> registeredHandlers;

    public EventDispatcherServiceImpl() {
        this.registeredHandlers = new HashSet<>();
    }

    @Override
    public void addHandler(EventHandler handler) {
        this.registeredHandlers.add(handler);
    }

    @Override
    public void dispatchEvent(Event event) {
        this.registeredHandlers.stream()
                .filter(h->h.canHandle(event.getClass()))
                .forEach(h->h.trigger(event));
    }
}

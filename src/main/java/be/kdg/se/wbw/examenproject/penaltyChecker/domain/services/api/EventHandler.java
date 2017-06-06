package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;

/**
 * Created by Wouter on 6/06/2017.
 */
public interface EventHandler<E extends Event> {
    void trigger(Event event);
    void subscribe(EventDispatcherService dispatcher);
    int hashCode();

    Class<E> getHandledType();
}

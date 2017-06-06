package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;

/**
 * Created by Wouter on 6/06/2017.
 */
public interface Service<E extends Event> {
    void fireEvent(E event);
    void addEventDispatcher(EventDispatcherService dispatcher);
}

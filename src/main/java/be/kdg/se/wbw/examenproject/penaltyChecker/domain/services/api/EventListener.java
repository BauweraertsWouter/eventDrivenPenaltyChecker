package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.Event;

/**
 * Created by Wouter on 6/06/2017.
 */
public interface EventListener<E extends Event> {
    void trigger(E event);
    void subscribe(EventDispatcher dispatcher);
}

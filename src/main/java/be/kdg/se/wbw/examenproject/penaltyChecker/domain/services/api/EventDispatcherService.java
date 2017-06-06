package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;

/**
 * The EventDispatcherService is the core of this application. It maintains a Set of EventHandlers and receives Events
 * from different services.
 * After receiving an Event, the EventDispatcherService dispatches the incoming event to the right EventHandler
 * to let them handle it.
 */
public interface EventDispatcherService {
    /**
     * Adds a EventHandler to the Set of EventHandlers
     * @param handler EventHandler to add
     */
    void addHandler(EventHandler handler);

    /**
     * Notifies the correct EventHandler to handle this Event.
     * @param event Event that has to be handled
     */
    void dispatchEvent(Event event);
}

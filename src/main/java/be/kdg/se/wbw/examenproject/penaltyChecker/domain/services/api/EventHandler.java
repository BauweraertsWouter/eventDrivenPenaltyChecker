package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;

/**
 * The EventHandler handles a certain type of Event. He will get triggered by the EventDispatcherService.
 */
public interface EventHandler {
    /**
     * This method will be triggered by the EventDispatcherService. When invoked, this method will make sure the Event
     * gets handled
     * @param event Event to handle
     */
    void trigger(Event event);

    /**
     * This method wil return the hashCode of this instance
     * @return int value
     */
    int hashCode();

    /**
     * This method will verify if an instance can handle an Event or not
     * @return true if it can be handled, false if not.
     */
    boolean canHandle(Class<? extends Event> eventClass);
}

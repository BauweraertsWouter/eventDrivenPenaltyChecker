package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation.todo;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventHandler;

/**
 * Created by Wouter on 7/06/2017.
 */
public class ViolationOutputService implements EventHandler {
    @Override
    public void trigger(Event event) {

    }

    @Override
    public boolean canHandle(Class<? extends Event> eventClass) {
        return false;
    }
}

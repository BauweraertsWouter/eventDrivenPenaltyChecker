package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation.todo;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventHandler;

public class ViolationOutputService implements EventHandler {
    @Override
    public void trigger(Event event) {

    }

    @Override
    public boolean canHandle(Class<? extends Event> eventClass) {
        return false;
    }
}

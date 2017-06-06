package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;

/**
 * Created by Wouter on 6/06/2017.
 */
public interface CommandService<E1 extends Event, E2 extends Event> extends Service<E1> {
}

package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base;

import java.time.LocalDateTime;

/**
 * Created by Wouter on 6/06/2017.
 */
public interface Event<T> {
    public T getEventDetails();
    public LocalDateTime getTimeStamp();
}

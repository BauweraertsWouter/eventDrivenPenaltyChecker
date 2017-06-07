package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base;

import java.time.LocalDateTime;

public interface Event<T> {
    T getEventDetails();
    LocalDateTime getTimeStamp();
}

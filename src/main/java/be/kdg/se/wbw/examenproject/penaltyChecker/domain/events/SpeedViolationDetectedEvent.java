package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.violation.Violation;

import java.time.LocalDateTime;


public class SpeedViolationDetectedEvent implements Event<Violation>{
    private final Violation eventBody;
    private final LocalDateTime timestamp;

    public SpeedViolationDetectedEvent(Violation eventBody) {
        this.eventBody = eventBody;
        timestamp = LocalDateTime.now();
    }

    @Override
    public Violation getEventDetails() {
        return eventBody;
    }

    @Override
    public LocalDateTime getTimeStamp() {
        return timestamp;
    }
}

package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.violation.Violation;

import java.time.LocalDateTime;

public class ViolationEvent implements Event<Violation> {
    private Violation eventBody;
    private LocalDateTime timestamp;

    public ViolationEvent(Violation violation) {
        eventBody = violation;
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

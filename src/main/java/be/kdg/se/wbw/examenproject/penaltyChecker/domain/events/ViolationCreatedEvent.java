package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.Violation;

import java.time.LocalDateTime;

public class ViolationCreatedEvent implements Event<Violation> {
    private Violation eventBody;
    private LocalDateTime timestamp;

    public ViolationCreatedEvent(Violation eventBody) {
        this.eventBody = eventBody;
        this.timestamp = LocalDateTime.now();
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

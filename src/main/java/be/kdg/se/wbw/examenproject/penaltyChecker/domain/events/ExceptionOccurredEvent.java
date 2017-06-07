package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;

import java.time.LocalDateTime;

public class ExceptionOccurredEvent implements Event<Exception> {
    private final Exception eventBody;
    private final LocalDateTime timestamp;

    public ExceptionOccurredEvent(Exception eventBody) {
        this.eventBody = eventBody;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public Exception getEventDetails() {
        return eventBody;
    }

    @Override
    public LocalDateTime getTimeStamp() {
        return timestamp;
    }

}

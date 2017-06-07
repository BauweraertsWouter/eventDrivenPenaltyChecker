package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;

import java.time.LocalDateTime;

public class ExceptionOccuredEvent implements Event<Exception> {
    private Exception eventBody;
    private LocalDateTime timestamp;

    public ExceptionOccuredEvent(Exception eventBody) {
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

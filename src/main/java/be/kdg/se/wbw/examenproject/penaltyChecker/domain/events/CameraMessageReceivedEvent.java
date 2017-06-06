package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraMessage;

import java.time.LocalDateTime;

public class CameraMessageReceivedEvent implements Event<CameraMessage> {
    private CameraMessage eventBody;
    private LocalDateTime timestamp;
    private Event innerEvent;

    public CameraMessageReceivedEvent(CameraMessage eventBody) {
        this.eventBody = eventBody;
        this.timestamp = LocalDateTime.now();
    }

    public Event getInnerEvent() {
        return innerEvent;
    }

    @Override
    public CameraMessage getEventDetails() {
        return eventBody;
    }

    @Override
    public LocalDateTime getTimeStamp() {
        return timestamp;
    }

    @Override
    public void addEvent(Event e) {
        innerEvent = e;
    }
}

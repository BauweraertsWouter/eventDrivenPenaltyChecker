package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraMessage;

import java.time.LocalDateTime;

public class CameraMessageReceivedEvent implements Event<CameraMessage> {
    private CameraMessage eventBody;
    private LocalDateTime timestamp;

    public CameraMessageReceivedEvent(CameraMessage eventBody) {
        this.eventBody = eventBody;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public CameraMessage getEventDetails() {
        return eventBody;
    }

    @Override
    public LocalDateTime getTimeStamp() {
        return timestamp;
    }
}

package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraDetails;

import java.time.LocalDateTime;

public class CameraDetailsRequiredEvent implements Event<CameraDetails> {
    private CameraDetails eventBody;
    private LocalDateTime timestamp;

    public CameraDetailsRequiredEvent(CameraDetails eventBody) {
        this.eventBody = eventBody;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public CameraDetails getEventDetails() {
        return eventBody;
    }

    @Override
    public LocalDateTime getTimeStamp() {
        return timestamp;
    }
}

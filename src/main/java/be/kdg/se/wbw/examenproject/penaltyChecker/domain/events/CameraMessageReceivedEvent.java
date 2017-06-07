package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraMessage;

import java.time.LocalDateTime;

public class CameraMessageReceivedEvent implements Event<CameraMessage> {
    private CameraMessage cameraMessage;
    private LocalDateTime timestamp;

    public CameraMessageReceivedEvent(CameraMessage cameraMessage) {
        this.cameraMessage = cameraMessage;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public CameraMessage getEventDetails() {
        return cameraMessage;
    }

    @Override
    public LocalDateTime getTimeStamp() {
        return timestamp;
    }
}

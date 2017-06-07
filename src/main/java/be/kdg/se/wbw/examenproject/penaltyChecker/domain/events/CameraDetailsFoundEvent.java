package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.CameraDetail;

import java.time.LocalDateTime;

public class CameraDetailsFoundEvent implements Event<CameraDetail> {
    private CameraDetail eventBody;
    private LocalDateTime timestamp;

    public CameraDetailsFoundEvent(CameraDetail eventBody) {
        this.eventBody = eventBody;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public CameraDetail getEventDetails() {
        return eventBody;
    }

    @Override
    public LocalDateTime getTimeStamp() {
        return timestamp;
    }
}

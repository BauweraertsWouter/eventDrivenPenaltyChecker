package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraMessage;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.CameraDetail;

import java.time.LocalDateTime;

public class GetDetailsForSpeedCheckEvent implements Event<CameraDetail> {
    private CameraDetail eventBody;
    private LocalDateTime timestamp;
    private Event innerEvent;

    public GetDetailsForSpeedCheckEvent(CameraDetail detail) {
        eventBody = detail;
        timestamp = LocalDateTime.now();
    }

    @Override
    public CameraDetail getEventDetails() {
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

    @Override
    public Event getInnerEvent() {
        return innerEvent;
    }
}

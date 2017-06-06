package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.CameraDetail;

import java.time.LocalDateTime;

public class GetLicensePlateDetailForLezEvent implements Event<CameraDetail> {
    private CameraDetail cameraDetail;
    private Event innerEvent;
    private LocalDateTime timeStamp;

    public GetLicensePlateDetailForLezEvent(CameraDetail detail) {
        cameraDetail = detail;
        timeStamp = LocalDateTime.now();
    }

    @Override
    public CameraDetail getEventDetails() {
        return cameraDetail;
    }

    @Override
    public LocalDateTime getTimeStamp() {
        return timeStamp;
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

package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.LezCheckLicensePlateRequestData;

import java.time.LocalDateTime;

public class GetLicensePlateDetailForLezEvent implements Event<LezCheckLicensePlateRequestData> {
    private LezCheckLicensePlateRequestData eventBody;
    private LocalDateTime timeStamp;

    public GetLicensePlateDetailForLezEvent(LezCheckLicensePlateRequestData eventBody) {
        this.eventBody = eventBody;
        timeStamp = LocalDateTime.now();
    }

    @Override
    public LezCheckLicensePlateRequestData getEventDetails() {
        return eventBody;
    }

    @Override
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

}

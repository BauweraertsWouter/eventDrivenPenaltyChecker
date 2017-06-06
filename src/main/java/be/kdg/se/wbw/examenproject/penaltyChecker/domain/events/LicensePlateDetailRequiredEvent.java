package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.RequestLicensePlateInfoMessage;

import java.time.LocalDateTime;

public class LicensePlateDetailRequiredEvent implements Event<RequestLicensePlateInfoMessage> {
    private RequestLicensePlateInfoMessage eventBody;
    private LocalDateTime timestamp;

    public LicensePlateDetailRequiredEvent(RequestLicensePlateInfoMessage eventBody) {
        this.eventBody = eventBody;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public RequestLicensePlateInfoMessage getEventDetails() {
        return eventBody;
    }

    @Override
    public LocalDateTime getTimeStamp() {
        return timestamp;
    }
}

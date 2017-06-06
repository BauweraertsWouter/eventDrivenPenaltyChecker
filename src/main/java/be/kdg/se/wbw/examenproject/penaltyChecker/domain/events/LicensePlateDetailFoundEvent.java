package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.LicensePlateDetails;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.PreviousCameraRequestMessage;

import java.time.LocalDateTime;


public class LicensePlateDetailFoundEvent implements Event<LicensePlateDetails> {
    private LicensePlateDetails eventBody;
    private LocalDateTime timestamp;

    public LicensePlateDetailFoundEvent(LicensePlateDetails eventBody) {
        this.eventBody = eventBody;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public LicensePlateDetails getEventDetails() {
        return eventBody;
    }

    @Override
    public LocalDateTime getTimeStamp() {
        return timestamp;
    }
}

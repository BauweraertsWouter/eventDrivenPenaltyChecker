package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.LicensePlateDetails;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.PreviousCameraRequestMessage;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.Violation;

import java.time.LocalDateTime;

public class PreviousCameraMessageRequiredEvent implements Event<PreviousCameraRequestMessage> {
    private PreviousCameraRequestMessage eventBody;
    private LocalDateTime timestamp;

    public PreviousCameraMessageRequiredEvent(PreviousCameraRequestMessage eventBody) {
        this.eventBody = eventBody;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public PreviousCameraRequestMessage getEventDetails() {
        return eventBody;
    }

    @Override
    public LocalDateTime getTimeStamp() {
        return timestamp;
    }
}

package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraMessage;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.PreviousCameraMessage;

import java.time.LocalDateTime;

public class PreviousCameraMessageFoundEvent implements Event<PreviousCameraMessage> {
    public PreviousCameraMessageFoundEvent(CameraMessage previousMessage, PreviousCameraMessageRequiredEvent event) {

    }

    @Override
    public PreviousCameraMessage getEventDetails() {
        return null;
    }

    @Override
    public LocalDateTime getTimeStamp() {
        return null;
    }
}

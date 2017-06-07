package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.LezData;

import java.time.LocalDateTime;

public class LezCheckEvent implements Event<LezData> {
    private final LezData eventBody;
    private final LocalDateTime timestamp;

    public LezCheckEvent(LezData lezData) {
        eventBody = lezData;
        timestamp = LocalDateTime.now();
    }

    @Override
    public LezData getEventDetails() {
        return eventBody;
    }

    @Override
    public LocalDateTime getTimeStamp() {
        return timestamp;
    }

}

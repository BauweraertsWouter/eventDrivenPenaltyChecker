package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.LezData;

import java.time.LocalDateTime;

public class LezCheckEvent implements Event<LezData> {
    private LezData eventBody;
    private LocalDateTime timestamp;
    private Event innerEvent;

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

    @Override
    public void addEvent(Event e) {
        innerEvent = e;
    }

    @Override
    public Event getInnerEvent() {
        return innerEvent;
    }
}
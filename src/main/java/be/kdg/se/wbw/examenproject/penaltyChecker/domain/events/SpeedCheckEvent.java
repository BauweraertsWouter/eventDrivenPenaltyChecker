package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.SpeedCheckData;

import java.time.LocalDateTime;


public class SpeedCheckEvent implements Event<SpeedCheckData> {
    private final SpeedCheckData speedCheckData;
    private final LocalDateTime timeStamp;

    public SpeedCheckEvent(SpeedCheckData speedCheckData) {
        this.speedCheckData = speedCheckData;
        timeStamp = LocalDateTime.now();
    }

    @Override
    public SpeedCheckData getEventDetails() {
        return speedCheckData;
    }

    @Override
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

}

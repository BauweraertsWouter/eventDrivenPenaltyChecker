package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.OutputMessageDto;

import java.time.LocalDateTime;

public class MessageOutputRequestedEvent implements Event<OutputMessageDto> {
    private OutputMessageDto eventBody;
    private LocalDateTime timestamp;

    public MessageOutputRequestedEvent(OutputMessageDto eventBody) {
        this.eventBody = eventBody;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public OutputMessageDto getEventDetails() {
        return eventBody;
    }

    @Override
    public LocalDateTime getTimeStamp() {
        return timestamp;
    }
}

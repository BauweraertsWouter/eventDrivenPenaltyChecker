package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraMessage;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.api.TypeMapper;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.CameraMessageDto;

/**
 * Created by Wouter on 6/06/2017.
 */
public class CameraMessageService implements EventHandler {
    private CameraMessageReceiver cameraMessageReceiver;
    private CameraMessageCache cameraMessageCache;
    private TypeMapper<CameraMessageDto, CameraMessage>
    @Override
    public void trigger(Event event) {

    }

    @Override
    public boolean canHandle(Class<? extends Event> eventClass) {
        return false;
    }
}

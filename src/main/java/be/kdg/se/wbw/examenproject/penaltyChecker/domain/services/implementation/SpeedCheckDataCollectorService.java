package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.GetPreviousMessageForSpeedPenaltyCheckEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.SpeedCheckEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraMessage;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.SpeedCheckData;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.CameraDetail;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.CameraMessageCache;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventDispatcherService;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.function.Consumer;

@Component
public class SpeedCheckDataCollectorService implements EventHandler {
    private static final Logger logger = Logger.getLogger(SpeedCheckDataCollectorService.class);

    private final Class handledType;
    private final EventDispatcherService dispatcherService;
    private final CameraMessageCache messageCache;

    @Autowired
    public SpeedCheckDataCollectorService(EventDispatcherService dispatcherService, CameraMessageCache messageCache) {
        this.dispatcherService = dispatcherService;
        this.messageCache = messageCache;
        handledType = GetPreviousMessageForSpeedPenaltyCheckEvent.class;
    }

    @PostConstruct
    private void registerToDispatcher(){
        dispatcherService.addHandler(this);
    }

    @Override
    public void trigger(Event event) {
        GetPreviousMessageForSpeedPenaltyCheckEvent myEvent = (GetPreviousMessageForSpeedPenaltyCheckEvent)event;
        CameraDetail firstCamera = myEvent.getFirstCameraDetail();
        messageCache.findPreviousMessage(firstCamera.getCameraId(), myEvent.getSecondMessage().getLicensePlate())
                .ifPresent(sendSpeedCheckEvent(myEvent, firstCamera));
    }

    private Consumer<CameraMessage> sendSpeedCheckEvent(GetPreviousMessageForSpeedPenaltyCheckEvent myEvent, CameraDetail firstCamera) {
        return cameraMessage -> dispatcherService.dispatchEvent(
                new SpeedCheckEvent(new SpeedCheckData(
                        firstCamera,
                        myEvent.getSecondCameraDetail(),
                        cameraMessage,
                        myEvent.getSecondMessage()
                ))
        );
    }

    @Override
    public boolean canHandle(Class<? extends Event> eventClass) {
        return handledType.equals(eventClass);
    }
}

package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.CameraMessageReceivedEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.ExceptionOccuredEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraMessage;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventDispatcherService;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.api.Subscriber;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.api.TypeMapper;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.CameraMessageDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class CameraMessageInputService implements Subscriber {
    private static final Logger logger = Logger.getLogger(CameraMessageInputService.class);
    private TypeMapper<CameraMessageDto, CameraMessage> mapper;
    private EventDispatcherService eventDispatcherService;


    @Autowired
    public CameraMessageInputService(TypeMapper<CameraMessageDto, CameraMessage> mapper, EventDispatcherService eventDispatcherService) {
        this.mapper = mapper;
        this.eventDispatcherService = eventDispatcherService;
        logger.info("CameraMessageInputService created");
    }

    @Override
    public void notify(CameraMessageDto message) {
        Event event = null;
        try {
            CameraMessage cameraMessage = mapper.map(message);
            logger.info("CameraMessageDto mapped to CameraMessage");
            event = new CameraMessageReceivedEvent(cameraMessage);
            logger.info("CameraMessageReceivedEvent created");
        }catch (Exception e){
            logger.warn("Exception occurred in CameraMessageInputService");
            event = new ExceptionOccuredEvent(e);
        }finally {
            eventDispatcherService.dispatchEvent(event);
        }
    }
}

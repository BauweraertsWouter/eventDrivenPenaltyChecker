package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.ExceptionOccurredEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventDispatcherService;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ExceptionHandlerService implements EventHandler {
    private static final Logger logger = Logger.getLogger(ExceptionHandlerService.class);
    private final Class handledEvent;
    private final EventDispatcherService dispatcherService;

    @Autowired
    public ExceptionHandlerService(EventDispatcherService dispatcherService) {
        this.dispatcherService = dispatcherService;
        handledEvent = ExceptionOccurredEvent.class;
    }

    @PostConstruct
    private void registerOnDispatcher(){
        dispatcherService.addHandler(this);
    }

    @Override
    public void trigger(Event event) {
        ExceptionOccurredEvent exceptionEvent = (ExceptionOccurredEvent)event;
        logger.warn("ExceptionHandler was triggered with an exception: " + exceptionEvent.getEventDetails().getMessage());
    }

    @Override
    public boolean canHandle(Class<? extends Event> eventClass) {
        return handledEvent.equals(eventClass);
    }
}

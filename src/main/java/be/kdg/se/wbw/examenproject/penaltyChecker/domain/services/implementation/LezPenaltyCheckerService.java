package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.ExceptionOccuredEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.LezCheckEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.ViolationEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.LezData;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.violation.Violation;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.violation.ViolationType;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventDispatcherService;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class LezPenaltyCheckerService implements EventHandler {
    private static final Logger logger = Logger.getLogger(LezPenaltyCheckerService.class);

    private EventDispatcherService dispatcherService;
    private Class handledEvent;

    @Autowired
    public LezPenaltyCheckerService(EventDispatcherService dispatcherService) {
        this.dispatcherService = dispatcherService;
        handledEvent = LezCheckEvent.class;
    }

    @Override
    public void trigger(Event event) {
        logger.info("LezPenaltyCheckerService triggered");
        LezCheckEvent myEvent = (LezCheckEvent) event;
        checkLezViolation(myEvent.getEventDetails());
    }

    private void checkLezViolation(LezData eventDetails) {
        try {
            if (eventDetails.getCameraDetail().getEuroNorm() > eventDetails.getPlateDetails().getEuroNorm()) {
                logger.info("LezPenaltyCheckerService detected a Violation");
                dispatcherService.dispatchEvent(
                        new ViolationEvent(
                                new Violation(
                                        ViolationType.LEZ,
                                        eventDetails.getMessage().getTimestamp(),
                                        eventDetails.getPlateDetails().getLicensePlate(),
                                        eventDetails.getPlateDetails().getNationalNumer(),
                                        eventDetails.getCameraDetail().getLocation(),
                                        0,
                                        0,
                                        eventDetails.getPlateDetails().getEuroNorm(),
                                        eventDetails.getCameraDetail().getEuroNorm()
                                )
                        )
                );
            }
            logger.info("LezPenaltyCheckerService finished his job");
        } catch (Exception e) {
            logger.warn("Exception occurred in LezPenaltyCheckerService");
            dispatcherService.dispatchEvent(new ExceptionOccuredEvent(e));
        }

    }

    @Override
    public boolean canHandle(Class<? extends Event> eventClass) {
        return handledEvent.equals(eventClass);
    }
}

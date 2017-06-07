package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation;

import be.kdg.se.wbw.examenproject.penaltyChecker.adapters.api.LicensePlateServiceProxyAdapter;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.*;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraMessage;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.LezCheckLicensePlateRequestData;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.LezData;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.LicensePlateDetails;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.violation.Violation;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventDispatcherService;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventHandler;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.api.TypeMapper;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.LicensePlateDetailDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class LicensePlateDetailService implements EventHandler {
    private static final Logger logger = Logger.getLogger(LicensePlateDetailService.class);

    private final EventDispatcherService dispatcherService;
    private final LicensePlateServiceProxyAdapter proxyAdapter;
    private final TypeMapper<LicensePlateDetailDto, LicensePlateDetails> mapper;

    private final List<Class> handledTypes;

    @Autowired
    public LicensePlateDetailService(EventDispatcherService dispatcherService, LicensePlateServiceProxyAdapter proxyAdapter, TypeMapper<LicensePlateDetailDto, LicensePlateDetails> mapper) {
        this.dispatcherService = dispatcherService;
        this.proxyAdapter = proxyAdapter;
        this.mapper = mapper;
        handledTypes = new ArrayList<>();
        handledTypes.add(GetLicensePlateDetailForLezEvent.class);
        handledTypes.add(SpeedViolationDetectedEvent.class);
    }

    @PostConstruct
    private void registerToDispatcher(){
        dispatcherService.addHandler(this);
    }

    @Override
    public void trigger(Event event) {
        logger.info("Event received in LicensePlateDetailService");
        try {
            if (event.getClass().equals(SpeedViolationDetectedEvent.class))
                completeSpeedViolation(((SpeedViolationDetectedEvent) event).getEventDetails());
            else
                triggerLezCheck((GetLicensePlateDetailForLezEvent) event);
        }catch (Exception e){
            logger.warn("Exception occurred in LicensePlateDetailService");
            dispatcherService.dispatchEvent(new ExceptionOccurredEvent(e));
        }
    }

    private void triggerLezCheck(GetLicensePlateDetailForLezEvent event) {
        LezCheckLicensePlateRequestData licensePlateRequestData = event.getEventDetails();
        CameraMessage message = licensePlateRequestData.getCameraMessage();
        LicensePlateDetails plateDetails = mapper.map(proxyAdapter.get(message.getLicensePlate()));
        dispatcherService.dispatchEvent(new LezCheckEvent(new LezData(message, licensePlateRequestData.getDetail(), plateDetails)));
    }

    private void completeSpeedViolation(Violation violation) {
        LicensePlateDetails details = mapper.map(proxyAdapter.get(violation.getLicensePlate()));
        violation.setNationalNumber(details.getNationalNumber());
        dispatcherService.dispatchEvent(new ViolationEvent(violation));
    }

    @Override
    public boolean canHandle(Class<? extends Event> eventClass) {
        return handledTypes.contains(eventClass);
    }
}

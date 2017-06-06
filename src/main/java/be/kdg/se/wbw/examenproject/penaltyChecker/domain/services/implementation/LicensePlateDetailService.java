package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation;

import be.kdg.se.wbw.examenproject.penaltyChecker.adapters.api.LicensePlateServiceProxyAdapter;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.GetLicensePlateDetailForLezEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.LezCheckEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.SpeedViolationDetectedEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.ViolationEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraMessage;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.LezData;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.LicensePlateDetails;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.CameraDetail;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.violation.Violation;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventDispatcherService;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventHandler;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.api.TypeMapper;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.LicensePlateDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LicensePlateDetailService implements EventHandler {
    private EventDispatcherService dispatcherService;
    private LicensePlateServiceProxyAdapter proxyAdapter;
    private TypeMapper<LicensePlateDetailDto, LicensePlateDetails> mapper;

    private List<Class> handledTypes;

    @Autowired
    public LicensePlateDetailService(EventDispatcherService dispatcherService, LicensePlateServiceProxyAdapter proxyAdapter, TypeMapper<LicensePlateDetailDto, LicensePlateDetails> mapper) {
        this.dispatcherService = dispatcherService;
        this.proxyAdapter = proxyAdapter;
        this.mapper = mapper;
        handledTypes = new ArrayList<>();
        handledTypes.add(GetLicensePlateDetailForLezEvent.class);
        handledTypes.add(SpeedViolationDetectedEvent.class);
    }

    @Override
    public void trigger(Event event) {
        if (event.getClass().equals(SpeedViolationDetectedEvent.class))
            completeSpeedViolation(((SpeedViolationDetectedEvent)event).getEventDetails());
        else
            triggerLezCheck((GetLicensePlateDetailForLezEvent)event);
    }

    private void triggerLezCheck(GetLicensePlateDetailForLezEvent event) {
        CameraDetail cameraDetail = event.getEventDetails();
        CameraMessage message = (CameraMessage)event.getInnerEvent().getEventDetails();
        LicensePlateDetails plateDetails = mapper.map(proxyAdapter.get(message.getLicensePlate()));
        dispatcherService.dispatchEvent(new LezCheckEvent(new LezData(message, cameraDetail, plateDetails)));
    }

    private void completeSpeedViolation(Violation violation) {
        LicensePlateDetails details = mapper.map(proxyAdapter.get(violation.getLicensePlate()));
        violation.setNationalNumber(details.getNationalNumer());
        dispatcherService.dispatchEvent(new ViolationEvent(violation));
    }

    @Override
    public boolean canHandle(Class<? extends Event> eventClass) {
        return handledTypes.contains(eventClass);
    }
}

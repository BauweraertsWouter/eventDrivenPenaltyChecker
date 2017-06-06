package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api;

import be.kdg.se.wbw.examenproject.penaltyChecker.adapters.api.LicensePlateServiceProxyAdapter;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.GetLicensePlateDetailForLezEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.SpeedViolationDetectedEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.LicensePlateDetails;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.api.TypeMapper;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.LicensePlateDetailDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LicensePlateDetailService implements EventHandler {
    private EventDispatcherService dispatcherService;
    private LicensePlateServiceProxyAdapter proxyAdapter;
    private TypeMapper<LicensePlateDetailDto, LicensePlateDetails> mapper;

    private List<Class> handledTypes;

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

    }

    @Override
    public boolean canHandle(Class<? extends Event> eventClass) {
        return handledTypes.contains(eventClass);
    }
}

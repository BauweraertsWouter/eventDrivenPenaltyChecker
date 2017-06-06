package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api;

import be.kdg.se.wbw.examenproject.penaltyChecker.adapters.api.LicensePlateServiceProxyAdapter;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.LicensePlateDetails;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.api.TypeMapper;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.LicensePlateDetailDto;
import org.springframework.stereotype.Component;

@Component
public class LicensePlateDetailService implements EventHandler {
    private EventDispatcherService dispatcherService;
    private LicensePlateServiceProxyAdapter proxyAdapter;
    private TypeMapper<LicensePlateDetailDto, LicensePlateDetails> mapper;
    @Override
    public void trigger(Event event) {

    }

    @Override
    public boolean canHandle(Class<? extends Event> eventClass) {
        return false;
    }
}

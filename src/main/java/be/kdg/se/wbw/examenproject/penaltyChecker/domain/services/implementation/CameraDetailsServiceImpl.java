package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation;

import be.kdg.se.wbw.examenproject.penaltyChecker.adapters.api.CameraDetailsServiceProxyAdapter;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.CameraMessageReceivedEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.ExceptionOccuredEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.GetLicensePlateDetailForLezEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.CameraDetail;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.CameraDetailsCache;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.CameraDetailsService;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventDispatcherService;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventHandler;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.api.TypeMapper;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.CameraDetailDto;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CameraDetailsServiceImpl implements CameraDetailsService, EventHandler {
    private Class handledType;
    private CameraDetailsServiceProxyAdapter proxyAdapter;
    private CameraDetailsCache cameraDetailsCache;
    private EventDispatcherService eventDispatcherService;
    private TypeMapper<CameraDetailDto, CameraDetail> mapper;

    @Autowired
    public CameraDetailsServiceImpl(CameraDetailsServiceProxyAdapter proxyAdapter, CameraDetailsCache cameraDetailsCache, EventDispatcherService eventDispatcherService, TypeMapper<CameraDetailDto, CameraDetail> mapper) {
        this.proxyAdapter = proxyAdapter;
        this.cameraDetailsCache = cameraDetailsCache;
        this.eventDispatcherService = eventDispatcherService;
        this.mapper = mapper;
        handledType = CameraMessageReceivedEvent.class;
    }

    @PostConstruct
    private void registerToDispatcher() {
        eventDispatcherService.addHandler(this);
    }

    @Override
    public CameraDetail findCamera(int cameraId) {
        Optional<CameraDetail> cached = cameraDetailsCache.findCamera(cameraId);
        return cached.orElseGet(() -> mapper.map(proxyAdapter.get(cameraId)));
    }

    @Override
    public void trigger(Event event) {
        List<Event> newEvents = new ArrayList<>();
        try {
            CameraDetail detail = getCameraDetail((CameraMessageReceivedEvent) event);
            newEvents.addAll(getNewEvents(event, detail));
        } catch (Exception e) {
            newEvents.add(new ExceptionOccuredEvent(e));
        }
        newEvents.forEach(newEvent -> eventDispatcherService.dispatchEvent(newEvent));
    }

    private Collection<? extends Event> getNewEvents(Event event, CameraDetail detail) {
        List<Event> events = new ArrayList<>();
        if (detail.isCheckLez()) {
            Event e = new GetLicensePlateDetailForLezEvent(detail);
            e.addEvent(event);
            events.add(e);
        }
        if (detail.isCheckSpeed()) {
            Event e = new GetDetailsForSpeedCheckEvent(detail);
            e.addEvent(event);
            events.add(e);
        }
        return events;
    }

    @Override
    public boolean canHandle(Class<? extends Event> eventClass) {
        return handledType.equals(eventClass);
    }

    private CameraDetail getCameraDetail(CameraMessageReceivedEvent event) {
        CameraMessageReceivedEvent myEvent = event;
        CameraDetail detail = findCamera(myEvent.getEventDetails().getCameraId());
        setPossibleViolationType(detail);
        return detail;
    }

    private void setPossibleViolationType(CameraDetail detail) {
        if (detail.getEuroNorm() != -1)
            detail.activateLez();
        if (detail.getSegment() != null)
            detail.activateSpeed();
    }
}

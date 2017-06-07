package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation;

import be.kdg.se.wbw.examenproject.penaltyChecker.adapters.api.CameraDetailsServiceProxyAdapter;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.CameraMessageReceivedEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.ExceptionOccuredEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.GetPreviousMessageForSpeedPenaltyCheckEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.GetLicensePlateDetailForLezEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.LezCheckLicensePlateRequestData;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.CameraDetail;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.CameraDetailsCache;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.CameraDetailsService;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventDispatcherService;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventHandler;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.api.TypeMapper;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.CameraDetailDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class CameraDetailsServiceImpl implements CameraDetailsService, EventHandler {
    private static final Logger logger = Logger.getLogger(CameraDetailsServiceImpl.class);

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
        logger.info("CameraDetailsService registered with EventDispatcher");
    }

    @Override
    public CameraDetail findCamera(int cameraId) {
        Optional<CameraDetail> cached = cameraDetailsCache.findCamera(cameraId);
        return cached.orElseGet(() -> {
            CameraDetail detail = mapper.map(proxyAdapter.get(cameraId));
            cameraDetailsCache.addCamera(detail);
            return detail;
        });
    }

    @Override
    public Optional<CameraDetail> findPreviousCamera(int cameraId) {
        return cameraDetailsCache.findPreviousCamera(cameraId);
    }

    @Override
    public void trigger(Event event) {
        List<Event> newEvents = new ArrayList<>();
        try {
            CameraMessageReceivedEvent myEvent = (CameraMessageReceivedEvent) event;
            CameraDetail latestCameraDetail = getCameraDetail(myEvent);
            newEvents.addAll(getNewEvents(latestCameraDetail, myEvent));
        } catch (Exception e) {
            newEvents.add(new ExceptionOccuredEvent(e));
        }
        newEvents.forEach(newEvent -> eventDispatcherService.dispatchEvent(newEvent));
    }

    private Collection<? extends Event> getNewEvents(CameraDetail second, CameraMessageReceivedEvent event) {
        List<Event> events = new ArrayList<>();
        if (second.isCheckLez()) {
            Event e = new GetLicensePlateDetailForLezEvent(new LezCheckLicensePlateRequestData(second, event.getEventDetails()));
            events.add(e);
        }
        if (second.isCheckSpeed()) {
            createEventWhenThePreviousCameraIsBuffered(second, event, events);
        }
        return events;
    }

    private void createEventWhenThePreviousCameraIsBuffered(CameraDetail second, CameraMessageReceivedEvent myEvent, List<Event> events) {
        cameraDetailsCache.findPreviousCamera(second.getCameraId()).ifPresent(firstCameraDetail->
                events.add(
                        new GetPreviousMessageForSpeedPenaltyCheckEvent(
                                firstCameraDetail,
                                second,
                                myEvent.getEventDetails()
                        )
        ));
    }

    @Override
    public boolean canHandle(Class<? extends Event> eventClass) {
        return handledType.equals(eventClass);
    }

    private CameraDetail getCameraDetail(CameraMessageReceivedEvent event) {
        CameraDetail detail = findCamera(event.getEventDetails().getCameraId());
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

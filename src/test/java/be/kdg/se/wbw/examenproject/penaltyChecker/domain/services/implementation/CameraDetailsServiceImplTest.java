package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation;

import be.kdg.se.wbw.examenproject.penaltyChecker.adapters.api.CameraDetailsServiceProxyAdapter;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.CameraMessageReceivedEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.ExceptionOccuredEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.GetLicensePlateDetailForLezEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraMessage;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.CameraDetail;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.Segment;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.CameraDetailsCache;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventDispatcherService;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.api.TypeMapper;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.CameraDetailDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CameraDetailsServiceImplTest {
    @InjectMocks
    private CameraDetailsServiceImpl detailsService;
    @Mock
    private EventDispatcherService dispatcherService;
    @Mock
    private CameraDetailsServiceProxyAdapter proxyAdapter;
    @Mock
    private CameraDetailsCache cache;
    @Mock
    private TypeMapper<CameraDetailDto, CameraDetail> mapper;

    @Test
    public void findCamera_cachedId_doesNotCallProxy() throws Exception {
        when(cache.findCamera(1))
                .thenReturn(
                        Optional.of(
                                new CameraDetail(1, null, null, 5, LocalDateTime.now()
                                )
                        )
                );
        detailsService.findCamera(1);
        verify(proxyAdapter, never()).get(1);
    }

    @Test
    public void findCamera_notCachedId_CallsProxy() throws Exception {
        when(cache.findCamera(1))
                .thenReturn(Optional.empty());
        detailsService.findCamera(1);
        verify(proxyAdapter, times(1)).get(1);
    }

    @Test
    public void trigger_withNoSegment_dispachterReceivesGetLicensePlateForLezEvent() throws Exception {
        when(cache.findCamera(1))
                .thenReturn(
                        Optional.of(
                                new CameraDetail(1, null, null, 5, LocalDateTime.now()
                                )
                        )
                );
        detailsService.trigger(new CameraMessageReceivedEvent(new CameraMessage.CameraMessageBuilder()
                .withCameraId(1)
                .withLicensePlate("123")
                .withTimestamp(LocalDateTime.now())
                .build())
        );
        verify(dispatcherService).dispatchEvent(any(GetLicensePlateDetailForLezEvent.class));
    }

    @Test
    public void trigger_withSegment_dispachterReceivesGetDetailsForSpeedCheckEvent() throws Exception {
        when(cache.findCamera(1))
                .thenReturn(
                        Optional.of(
                                new CameraDetail(1, null, new Segment(2,25,25), -1, LocalDateTime.now()
                                )
                        )
                );
        detailsService.trigger(new CameraMessageReceivedEvent(new CameraMessage.CameraMessageBuilder()
                .withCameraId(1)
                .withLicensePlate("123")
                .withTimestamp(LocalDateTime.now())
                .build())
        );
        verify(dispatcherService).dispatchEvent(any(GetDetailsForSpeedCheckEvent.class));
    }

    @Test
    public void trigger_withSegmentAndEuronorm_dispachterReceivesTwoEvents() throws Exception {
        when(cache.findCamera(1))
                .thenReturn(
                        Optional.of(
                                new CameraDetail(1, null, new Segment(2,25,25), 3, LocalDateTime.now()
                                )
                        )
                );
        detailsService.trigger(new CameraMessageReceivedEvent(new CameraMessage.CameraMessageBuilder()
                .withCameraId(1)
                .withLicensePlate("123")
                .withTimestamp(LocalDateTime.now())
                .build())
        );
        verify(dispatcherService, times(2)).dispatchEvent(any(Event.class));
    }

    @Test
    public void canHandle() throws Exception {
        assertThat(detailsService.canHandle(CameraMessageReceivedEvent.class)).isTrue();
        assertThat(detailsService.canHandle(ExceptionOccuredEvent.class)).isFalse();
    }

}
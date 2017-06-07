package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api;

import be.kdg.se.wbw.examenproject.penaltyChecker.adapters.api.LicensePlateServiceProxyAdapter;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.*;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraMessage;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.LezCheckLicensePlateRequestData;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.LicensePlateDetails;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.CameraDetail;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.violation.Violation;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.violation.ViolationType;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation.LicensePlateDetailService;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.api.TypeMapper;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.LicensePlateDetailDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LicensePlateDetailServiceTest {
    @InjectMocks
    private LicensePlateDetailService service;
    @Mock
    private LicensePlateServiceProxyAdapter adapter;
    @Mock
    private EventDispatcherService dispatcherService;
    @Mock
    private TypeMapper<LicensePlateDetailDto, LicensePlateDetails> mapper;

    @Test
    public void canHandle_GivenExcepeptions_returnsExpectedResult() throws Exception {
        assertThat(service.canHandle(ExceptionOccuredEvent.class)).isFalse();
        assertThat(service.canHandle(GetLicensePlateDetailForLezEvent.class)).isTrue();
        assertThat(service.canHandle(SpeedViolationDetectedEvent.class)).isTrue();
    }

    @Test
    public void trigger_withLez_dispatcherReceivesLezCheckEvent() throws Exception {
        LezCheckLicensePlateRequestData requestData = new LezCheckLicensePlateRequestData(
                new CameraDetail(1, null, null, 3, LocalDateTime.now()),
                new CameraMessage.CameraMessageBuilder()
                    .withLicensePlate("123")
                    .withCameraId(1)
                    .build()
                );
        Event lez = new GetLicensePlateDetailForLezEvent(requestData);

        when(adapter.get("123")).thenReturn(new LicensePlateDetailDto.LicensePlateDetailDtoBuilder()
                .withEuroNorm(2)
                .withNationalNumber("654")
                .withPlateId("132")
                .build());

        service.trigger(lez);

        verify(dispatcherService).dispatchEvent(any(LezCheckEvent.class));
    }

    @Test
    public void trigger_withSpeedViolation_dispatcherReceivesViolationEvent() throws Exception {
        Event violation = new SpeedViolationDetectedEvent(new Violation(ViolationType.SPEED, LocalDateTime.now(), "132", null, 132,50,-1));
        when(adapter.get(any())).thenReturn(new LicensePlateDetailDto.LicensePlateDetailDtoBuilder()
                .withEuroNorm(2)
                .withNationalNumber("654")
                .withPlateId("132")
                .build());

        when(mapper.map(any())).thenReturn(new LicensePlateDetails("132","654",2));
        service.trigger(violation);
        verify(dispatcherService).dispatchEvent(any(ViolationEvent.class));
    }
}
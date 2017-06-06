package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.CameraMessageReceivedEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.ExceptionOccuredEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraMessage;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventDispatcherService;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.api.TypeMapper;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.CameraMessageDto;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.exceptions.MappingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CameraMessageInputServiceTest {
    @InjectMocks
    private CameraMessageInputService inputService;
    @Mock
    private TypeMapper<CameraMessageDto, CameraMessage> mapper;
    @Mock
    private EventDispatcherService dispatcherService;

    @Test
    public void notify_givenMessageDto_dispatcherReceivesCameraMessageReceivedEvent() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.now();
        CameraMessageDto dto = new CameraMessageDto.CameraMessageDtoBuilder()
                .withCameraId(1)
                .withLicensePlate("123")
                .withTimeStamp(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                .build();

        CameraMessage msg = new CameraMessage.CameraMessageBuilder()
                .withCameraId(1)
                .withLicensePlate("123")
                .withTimestamp(localDateTime)
                .build();
        when(mapper.map(dto)).thenReturn(msg);

        inputService.notify(dto);
        verify(dispatcherService).dispatchEvent(any(CameraMessageReceivedEvent.class));
    }

    @Test
    public void notify_givenMessageDto_dispatcherReceivesErrorOccurredEvent() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.now();
        CameraMessageDto dto = new CameraMessageDto.CameraMessageDtoBuilder()
                .withCameraId(1)
                .withLicensePlate("123")
                .withTimeStamp(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                .build();

        when(mapper.map(dto)).thenThrow(new MappingException("blabla"));

        inputService.notify(dto);
        verify(dispatcherService).dispatchEvent(any(ExceptionOccuredEvent.class));
    }

}
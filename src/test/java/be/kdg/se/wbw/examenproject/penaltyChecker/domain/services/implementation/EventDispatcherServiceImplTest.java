package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.CameraDetailsFoundEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.ExceptionOccuredEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.CameraDetail;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventDispatcherServiceImplTest {

    private EventDispatcherServiceImpl dispatcherService;
    private Event event;

    @Mock
    private EventHandler exceptionEventHandler;

    @Before
    public void setUp() throws Exception {
        dispatcherService = new EventDispatcherServiceImpl();
        dispatcherService.addHandler(exceptionEventHandler);
    }

    @Test
    public void dispatchEvent_givenExceptionOccuredEvent_exceptionEventHandlerGetsNotified() throws Exception {
        event = new ExceptionOccuredEvent(new Exception());
        when(exceptionEventHandler.canHandle(event.getClass())).thenReturn(true);
        dispatcherService.dispatchEvent(event);
        verify(exceptionEventHandler, times(1)).trigger(event);
    }

    @Test
    public void dispatchEvent_givenViolationCreatedEvent_exceptionEventHandlerDoesNotGetNotified() throws Exception {
        event = new CameraDetailsFoundEvent(new CameraDetail(1, null, null, -1, LocalDateTime.now()));
        when(exceptionEventHandler.canHandle(event.getClass())).thenReturn(false);
        dispatcherService.dispatchEvent(event);
        verify(exceptionEventHandler, never()).trigger(event);
    }

}
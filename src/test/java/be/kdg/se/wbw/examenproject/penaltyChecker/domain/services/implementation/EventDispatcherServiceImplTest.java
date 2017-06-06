package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.CameraDetailsRetrievedEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.ExceptionOccuredEvent;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraDetails;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.EventHandler;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventDispatcherServiceImplTest {

    private EventDispatcherServiceImpl dispatcherService;
    private Event event;

    @Mock
    private EventHandler<ExceptionOccuredEvent> exceptionEventHandler;

    @Before
    public void setUp() throws Exception {
        dispatcherService = new EventDispatcherServiceImpl();
        dispatcherService.addHandler(exceptionEventHandler);
        when(exceptionEventHandler.getHandledType()).thenReturn(ExceptionOccuredEvent.class);
    }

    @Test
    public void dispatchEvent_givenExceptionOccuredEvent_exceptionEventHandlerGetsNotified() throws Exception {
        event = new ExceptionOccuredEvent(new Exception());
        dispatcherService.dispatchEvent(event);
        verify(exceptionEventHandler, times(1)).trigger(event);
    }

    @Test
    public void dispatchEvent_givenViolationCreatedEvent_exceptionEventHandlerDoesNotGetNotified() throws Exception {
        event = new CameraDetailsRetrievedEvent(new CameraDetails());
        dispatcherService.dispatchEvent(event);
        verify(exceptionEventHandler, never()).trigger(event);
    }

}
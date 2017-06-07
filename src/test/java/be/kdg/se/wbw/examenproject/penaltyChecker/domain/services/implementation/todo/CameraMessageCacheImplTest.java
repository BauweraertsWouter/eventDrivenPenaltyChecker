package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation.todo;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraMessage;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation.caching.CameraMessageCacheImpl;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class CameraMessageCacheImplTest {
    private CameraMessageCacheImpl buffer;

    @Before
    public void setUp() throws Exception {
        buffer = new CameraMessageCacheImpl();
        buffer.setCleanIntervalMinutes(2);
    }

    @Test
    public void findCamera_noCameraAdded_returnsEmptyOptional() throws Exception {
        assertThat(buffer.findPreviousMessage(1,"132").isPresent()).isFalse();
    }

    @Test
    public void findCamera_messagePresent_returnsCameraMessageOnlyOnce() throws Exception {
        CameraMessage cameraMessage = new CameraMessage.CameraMessageBuilder()
                .withCameraId(1)
                .withLicensePlate("123")
                .build();

        buffer.add(cameraMessage);
        assertThat(buffer.findPreviousMessage(1,"123").get()).isEqualToComparingFieldByField(cameraMessage);
        assertThat(buffer.findPreviousMessage(1,"123").isPresent()).isFalse();
    }

    @Test
    public void findCamera_messageAddedTwice_returnsCameraMessageOnlyOnce() throws Exception {
        CameraMessage cameraMessage = new CameraMessage.CameraMessageBuilder()
                .withCameraId(1)
                .withLicensePlate("123")
                .build();

        buffer.add(cameraMessage);
        buffer.add(cameraMessage);
        assertThat(buffer.findPreviousMessage(1,"123").get()).isEqualToComparingFieldByField(cameraMessage);
        assertThat(buffer.findPreviousMessage(1,"123").isPresent()).isFalse();
    }

    @Test
    public void cleanUp_noMessageHasToBeRemoved_MessageStillInBuffer() throws Exception {
        CameraMessage cameraMessage = new CameraMessage.CameraMessageBuilder()
                .withCameraId(1)
                .withLicensePlate("123")
                .withTimestamp(LocalDateTime.now().plusMinutes(2))
                .build();

        buffer.add(cameraMessage);
        buffer.cleanUp();
        assertThat(buffer.findPreviousMessage(1,"123").isPresent()).isTrue();
    }

    @Test
    public void cleanUp_messageHasToBeRemoved_MessageNoLongerInBuffer() throws Exception {
        CameraMessage cameraMessage = new CameraMessage.CameraMessageBuilder()
                .withCameraId(1)
                .withLicensePlate("123")
                .withTimestamp(LocalDateTime.now().minusMinutes(4))
                .build();

        buffer.add(cameraMessage);
        buffer.cleanUp();
        assertThat(buffer.findPreviousMessage(1,"123").isPresent()).isFalse();
    }
}
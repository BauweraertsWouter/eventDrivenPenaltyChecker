package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation.caching;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraMessage;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.CameraMessageCache;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CameraMessageCacheImpl implements CameraMessageCache {
    private final List<CameraMessage> messageBuffer = Collections.synchronizedList(new ArrayList<>());
    private int interval;

    @Override
    public Optional<CameraMessage> findPreviousMessage(int cameraId, String licensePlate) {
        Optional<CameraMessage> message = messageBuffer.stream()
                .filter(m -> m.getCameraId() == cameraId)
                .filter(m -> m.getLicensePlate().equals(licensePlate))
                .findFirst();
        message.ifPresent(messageBuffer::remove);
        return message;
    }

    @Override
    public void add(CameraMessage cameraMessage) {
        findPreviousMessage(cameraMessage.getCameraId(), cameraMessage.getLicensePlate());
        messageBuffer.add(cameraMessage);
    }

    @Override
    public void cleanUp() {
        LocalDateTime current = LocalDateTime.now().minusMinutes(interval);
        new ArrayList<>(messageBuffer).stream()
                .filter(m -> m.getTimestamp().isBefore(current))
                .forEach(messageBuffer::remove);
    }

    @Override
    public void setCleanIntervalMinutes(int minutes) {
        this.interval = minutes;
    }
}

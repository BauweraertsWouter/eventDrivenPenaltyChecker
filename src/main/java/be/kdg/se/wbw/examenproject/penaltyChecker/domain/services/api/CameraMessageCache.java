package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraMessage;

import java.util.Optional;

public interface CameraMessageCache {
    Optional<CameraMessage> findPreviousMessage(int cameraId, String licensePlate);
    void add(CameraMessage cameraMessage);
}

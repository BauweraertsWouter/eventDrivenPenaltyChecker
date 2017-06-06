package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraMessage;

/**
 * Created by Wouter on 6/06/2017.
 */
public interface CameraMessageCache {
    CameraMessage findPreviousMessage(int cameraId, String licensePlate);
}

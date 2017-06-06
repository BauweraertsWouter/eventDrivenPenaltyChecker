package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraDetails;

public interface CameraService {
    CameraDetails findCamera(int cameraId);
}

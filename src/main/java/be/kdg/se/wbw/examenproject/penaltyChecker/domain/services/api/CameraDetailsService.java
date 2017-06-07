package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.CameraDetail;

import java.util.Optional;

public interface CameraDetailsService {
    CameraDetail findCamera(int cameraId);
    Optional<CameraDetail> findPreviousCamera(int cameraId);
}

package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.CameraDetail;

import java.util.Optional;

/**
 * The CameraDetailsCache is a caching service which will cache CameraDetail retreived from the proxy for a
 * configurable time.
 */
public interface CameraDetailsCache {
    Optional<CameraDetail> findCamera(int cameraId);
    Optional<CameraDetail> findPreviousCamera(int cameraId);
    void addCamera(CameraDetail cameraDetail);
    void clear();

    void setIntervalMinutes(int i);
}

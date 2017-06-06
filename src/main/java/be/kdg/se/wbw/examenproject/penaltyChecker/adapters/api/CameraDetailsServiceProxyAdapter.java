package be.kdg.se.wbw.examenproject.penaltyChecker.adapters.api;

import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.CameraDetailDto;

/**
 * The CameraDetailsServiceProxyAdapter is an adapter interface to connect with an external CameraDetailsServiceProxy
 */
public interface CameraDetailsServiceProxyAdapter {
    /**
     * Invokes a HTTP.GET on the external service, using the provided cameraId
     * @param cameraId the ID of the camera you
     * @return instance of CameraDetailDto with the details of the requested camera
     */
    CameraDetailDto get(int cameraId);
}

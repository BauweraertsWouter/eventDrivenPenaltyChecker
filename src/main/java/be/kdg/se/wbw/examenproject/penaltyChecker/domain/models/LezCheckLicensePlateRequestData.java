package be.kdg.se.wbw.examenproject.penaltyChecker.domain.models;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.CameraDetail;

public class LezCheckLicensePlateRequestData {
    private final CameraDetail detail;
    private final CameraMessage cameraMessage;

    public LezCheckLicensePlateRequestData(CameraDetail detail, CameraMessage cameraMessage) {
        this.detail = detail;
        this.cameraMessage = cameraMessage;
    }

    public CameraDetail getDetail() {
        return detail;
    }

    public CameraMessage getCameraMessage() {
        return cameraMessage;
    }
}

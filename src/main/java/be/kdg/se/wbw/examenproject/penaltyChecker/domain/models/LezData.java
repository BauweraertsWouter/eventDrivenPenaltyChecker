package be.kdg.se.wbw.examenproject.penaltyChecker.domain.models;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.CameraDetail;

public class LezData {
    private final CameraMessage message;
    private final CameraDetail cameraDetail;
    private final LicensePlateDetails plateDetails;

    public LezData(CameraMessage message, CameraDetail cameraDetail, LicensePlateDetails plateDetails) {
        this.message=message;
        this.cameraDetail = cameraDetail;
        this.plateDetails = plateDetails;
    }

    public CameraMessage getMessage() {
        return message;
    }

    public CameraDetail getCameraDetail() {
        return cameraDetail;
    }

    public LicensePlateDetails getPlateDetails() {
        return plateDetails;
    }
}

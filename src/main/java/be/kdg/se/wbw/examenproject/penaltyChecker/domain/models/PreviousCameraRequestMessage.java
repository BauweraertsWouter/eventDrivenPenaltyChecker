package be.kdg.se.wbw.examenproject.penaltyChecker.domain.models;

public class PreviousCameraRequestMessage {
    private int cameraId;
    private String licensePlate;

    public PreviousCameraRequestMessage(int cameraId, String licensePlate) {
        this.cameraId = cameraId;
        this.licensePlate = licensePlate;
    }

    public int getCameraId() {
        return cameraId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
}

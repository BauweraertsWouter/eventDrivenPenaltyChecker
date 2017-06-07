package be.kdg.se.wbw.examenproject.penaltyChecker.domain.models;


import java.time.LocalDateTime;

public class CameraMessage {
    private final LocalDateTime timestamp;
    private final String licensePlate;
    private final int cameraId;

    private CameraMessage(LocalDateTime timestamp, String licensePlate, int cameraId) {
        this.timestamp = timestamp;
        this.licensePlate = licensePlate;
        this.cameraId = cameraId;
    }

    public LocalDateTime getTimestamp() { return timestamp; }

    public String getLicensePlate() { return licensePlate; }

    public int getCameraId() { return cameraId; }

    public static class CameraMessageBuilder {
        private String licensePlate;
        private LocalDateTime timestamp = LocalDateTime.now();

        private int cameraId;

        public CameraMessageBuilder withLicensePlate(String licensePlate) {
            this.licensePlate=licensePlate;
            return this;
        }

        public CameraMessageBuilder withTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public CameraMessageBuilder withCameraId(int cameraId) {
            this.cameraId=cameraId;
            return this;
        }

        public CameraMessage build() {
            return new CameraMessage(timestamp, licensePlate, cameraId);
        }
    }
}

package be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto;

import java.util.Date;

public class CameraMessageDto {
    private Date timestamp;
    private String licensePlate;
    private int cameraId;

    private CameraMessageDto(Date timestamp, String licensePlate, int cameraId) {
        this.timestamp=timestamp;
        this.licensePlate = licensePlate;
        this.cameraId=cameraId;
    }

    public CameraMessageDto() {
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }

    public Date getTimestamp() { return timestamp; }

    public String getLicensePlate() { return licensePlate; }

    public int getCameraId() { return cameraId; }

    @SuppressWarnings("SameParameterValue")
    public static class CameraMessageDtoBuilder {
        private Date date;
        private String licensePlate;
        private int cameraId;

        public CameraMessageDtoBuilder withTimeStamp(Date date) {
            this.date = date;
            return this;
        }

        public CameraMessageDtoBuilder withLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public CameraMessageDtoBuilder withCameraId(int cameraId) {
            this.cameraId=cameraId;
            return this;
        }

        public CameraMessageDto build() {
            return new CameraMessageDto(date, licensePlate, cameraId);
        }
    }
}

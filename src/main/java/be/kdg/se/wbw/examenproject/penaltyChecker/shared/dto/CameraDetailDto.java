package be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto;

import java.time.LocalDateTime;

public class CameraDetailDto {
    private final int cameraId;
    private final LocationDto location;
    private final SegmentDto segment;
    private final int euroNorm;
    private final LocalDateTime retrievingTimestamp;

    private CameraDetailDto(int cameraId, LocationDto location, SegmentDto segment, int euroNorm, LocalDateTime time) {
        this.cameraId = cameraId;
        this.location = location;
        this.segment = segment;
        this.euroNorm = euroNorm;
        this.retrievingTimestamp = time;
    }

    public int getCameraId() { return cameraId; }

    public LocationDto getLocation() { return location; }

    public SegmentDto getSegment() { return segment; }

    public int getEuroNorm() { return euroNorm; }

    public LocalDateTime getRetrievingTimestamp() { return retrievingTimestamp; }

    public static class CameraDetailDtoBuilder {
        private int cameraId;
        private LocationDto location;
        private SegmentDto segment;
        private int euroNorm;
        private LocalDateTime timestamp = LocalDateTime.now(); //added for testing purposes only

        public CameraDetailDtoBuilder withCameraId(int cameraId) {
            this.cameraId = cameraId;
            return this;
        }

        public CameraDetailDtoBuilder withLocationDto(LocationDto location) {
            this.location = location;
            return this;
        }

        public CameraDetailDtoBuilder withSegmentDto(SegmentDto segment) {
            this.segment = segment;
            return this;
        }

        public CameraDetailDtoBuilder withEuroNorm(int euroNorm) {
            this.euroNorm = euroNorm;
            return this;
        }

        public CameraDetailDtoBuilder withTimeStamp(LocalDateTime time) {
            this.timestamp = time;
            return this;
        }

        public CameraDetailDto build() {
            return new CameraDetailDto(cameraId, location, segment, euroNorm, timestamp);
        }
    }
}

package be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto;

public class SegmentDto {
    private final int connectedCameraId;
    private final int distance;
    private final int speedLimit;

    private SegmentDto(int connectedCameraId, int distance, int speedLimit) {
        this.connectedCameraId = connectedCameraId;
        this.distance = distance;
        this.speedLimit = speedLimit;
    }

    public int getConnectedCameraId() { return connectedCameraId; }

    public int getDistance() { return distance; }

    public int getSpeedLimit() { return speedLimit; }

    public static class SegmentDtoBuilder{
        private int connectedCameraId;
        private int distance;
        private int speedLimit;

        public SegmentDtoBuilder withConnectedCameraId(int connectedCameraId){
            this.connectedCameraId=connectedCameraId;
            return this;
        }
        public SegmentDtoBuilder withDistance(int distance){
            this.distance=distance;
            return this;
        }
        public SegmentDtoBuilder withSpeedLimit(int speedLimit){
            this.speedLimit=speedLimit;
            return this;
        }

        public SegmentDto build(){
            return new SegmentDto(connectedCameraId, distance, speedLimit);
        }
    }
}

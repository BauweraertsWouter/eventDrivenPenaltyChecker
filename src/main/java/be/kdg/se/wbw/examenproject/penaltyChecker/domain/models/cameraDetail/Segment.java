package be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail;

public class Segment {
    private final int connectedCameraId;
    private final int distance;
    private final int speedLimit;

    public Segment(int connectedCameraId, int distance, int speedLimit) {
        this.connectedCameraId = connectedCameraId;
        this.distance = distance;
        this.speedLimit = speedLimit;
    }

    public int getConnectedCameraId() {
        return connectedCameraId;
    }

    public int getDistance() {
        return distance;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }
}

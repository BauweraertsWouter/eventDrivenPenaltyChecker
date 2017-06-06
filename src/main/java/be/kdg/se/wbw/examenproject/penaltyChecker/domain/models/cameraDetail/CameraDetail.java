package be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.violation.ViolationType;

import java.time.LocalDateTime;

public class CameraDetail {
    private int cameraId;
    private Location location;
    private Segment segment;
    private int euroNorm;
    private LocalDateTime retrievingTimestamp;
    private boolean checkLez;
    private boolean checkSpeed;

    public CameraDetail(int cameraId, Location location, Segment segment, int euroNorm, LocalDateTime time) {
        this.cameraId = cameraId;
        this.location = location;
        this.segment = segment;
        this.euroNorm = euroNorm;
        this.retrievingTimestamp = time;
    }

    public int getCameraId() { return cameraId; }

    public Location getLocation() { return location; }

    public Segment getSegment() { return segment; }

    public int getEuroNorm() { return euroNorm; }

    public LocalDateTime getRetrievingTimestamp() { return retrievingTimestamp; }

    public boolean isCheckLez() { return checkLez; }

    public boolean isCheckSpeed() { return checkSpeed; }

    public void activateLez() { checkLez = true; }

    public void activateSpeed(){ checkSpeed = true; }
}

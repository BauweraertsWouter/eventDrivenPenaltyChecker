package be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.violation;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.Location;

import java.time.LocalDateTime;

public class Violation {
    private ViolationType violationType;
    private LocalDateTime timestamp;
    private String licensePlate;
    private String nationalNumber;
    private Location location;
    private int speed;
    private int maxSpeed;
    private int euroNorm;
    private int maxEuroNorm;

    public Violation(ViolationType violationType, LocalDateTime timestamp, String licensePlate, String nationalNumber,
                     Location location, int speed, int maxSpeed, int euroNorm, int maxEuroNorm) {
        this.violationType = violationType;
        this.timestamp = timestamp;
        this.licensePlate = licensePlate;
        this.nationalNumber = nationalNumber;
        this.location = location;
        this.speed = speed;
        this.maxSpeed = maxSpeed;
        this.euroNorm = euroNorm;
        this.maxEuroNorm = maxEuroNorm;
    }

    public Violation(ViolationType violationType, LocalDateTime timestamp, String licensePlate,
                     Location location, int speed, int maxSpeed, int maxEuroNorm) {
        this.violationType = violationType;
        this.timestamp = timestamp;
        this.licensePlate = licensePlate;
        this.location = location;
        this.speed = speed;
        this.maxSpeed = maxSpeed;
        this.maxEuroNorm = maxEuroNorm;
    }

    public ViolationType getViolationType() { return violationType; }

    public LocalDateTime getTimestamp() { return timestamp; }

    public String getLicensePlate() { return licensePlate; }

    public String getNationalNumber() { return nationalNumber; }

    public Location getLocation() { return location; }

    public int getSpeed() { return speed; }

    public int getMaxSpeed() { return maxSpeed; }

    public int getEuroNorm() { return euroNorm; }

    public int getMaxEuroNorm() { return maxEuroNorm; }

    public void setViolationType(ViolationType violationType) {
        this.violationType = violationType;
    }

    public void setNationalNumber(String nationalNumber) {
        this.nationalNumber = nationalNumber;
    }

    public void setEuroNorm(int euroNorm) {
        this.euroNorm = euroNorm;
    }
}

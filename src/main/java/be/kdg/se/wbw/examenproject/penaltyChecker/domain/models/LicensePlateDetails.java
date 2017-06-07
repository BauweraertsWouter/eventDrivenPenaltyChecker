package be.kdg.se.wbw.examenproject.penaltyChecker.domain.models;

public class LicensePlateDetails {
    private final String licensePlate;
    private final String nationalNumber;
    private final int euroNorm;

    public LicensePlateDetails(String licensePlate, String nationalNumber, int euroNorm) {
        this.licensePlate = licensePlate;
        this.nationalNumber = nationalNumber;
        this.euroNorm = euroNorm;
    }

    public String getNationalNumber() {
        return nationalNumber;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getEuroNorm() {
        return euroNorm;
    }
}

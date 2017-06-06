package be.kdg.se.wbw.examenproject.penaltyChecker.domain.models;

public class LicensePlateDetails {
    private String licensePlate;
    private String nationalNumer;
    private int euroNorm;

    public LicensePlateDetails(String licensePlate, String nationalNumer, int euroNorm) {
        this.licensePlate = licensePlate;
        this.nationalNumer = nationalNumer;
        this.euroNorm = euroNorm;
    }

    public String getNationalNumer() {
        return nationalNumer;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getEuroNorm() {
        return euroNorm;
    }
}

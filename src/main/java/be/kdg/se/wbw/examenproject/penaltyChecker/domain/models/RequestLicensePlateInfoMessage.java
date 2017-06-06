package be.kdg.se.wbw.examenproject.penaltyChecker.domain.models;

public class RequestLicensePlateInfoMessage {
    private String licensePlate;

    public RequestLicensePlateInfoMessage(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
}

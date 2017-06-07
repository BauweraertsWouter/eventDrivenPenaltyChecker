package be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail;

public class Location {
    private final int latitude;
    private final int longitude;

    public Location(int latitude, int longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getLatitude() { return latitude; }

    public int getLongitude() { return longitude; }
}

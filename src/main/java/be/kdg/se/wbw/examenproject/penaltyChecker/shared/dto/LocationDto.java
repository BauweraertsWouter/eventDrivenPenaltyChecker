package be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto;

public class LocationDto {
    private final int latitude;
    private final int longitude;

    private LocationDto(int latitude, int longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getLatitude() { return latitude; }

    public int getLongitude() { return longitude; }

    public static class CameraLocationDtoBuilder{
        private int latitude;
        private int longitude;

        public CameraLocationDtoBuilder withLatitude (int latitude){
            this.latitude=latitude;
            return this;
        }
        public CameraLocationDtoBuilder withLongitude (int longitude){
            this.longitude=longitude;
            return this;
        }

        public LocationDto build(){
            return new LocationDto(latitude, longitude);
        }
    }
}

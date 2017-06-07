package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation.mappers;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.Location;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.api.TypeMapper;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.LocationDto;
import org.springframework.stereotype.Component;

@Component
public class LocationDtoToLocationMapper implements TypeMapper<LocationDto, Location> {
    @Override
    public Location map(LocationDto locationDto) {
        return new Location(locationDto.getLatitude(), locationDto.getLongitude());
    }
}

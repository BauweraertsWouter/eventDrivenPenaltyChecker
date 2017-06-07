package be.kdg.se.wbw.examenproject.penaltyChecker.adapters.implementation.mappers;

import be.kdg.se.wbw.examenproject.penaltyChecker.shared.api.TypeMapper;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.CameraDetailDto;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.LocationDto;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.SegmentDto;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JSONObjectToCameraDetailDtoMapperService implements TypeMapper<JSONObject, CameraDetailDto> {
    @Override
    public CameraDetailDto map(JSONObject incoming) {
        return new CameraDetailDto.CameraDetailDtoBuilder()
                .withCameraId(incoming.getInt("cameraId"))
                .withEuroNorm(getEuroNorm(incoming))
                .withSegmentDto(getSegmentDto(incoming))
                .withLocationDto(getLocationDto(incoming.getJSONObject("location")))
                .build();
    }

    private int getEuroNorm(JSONObject incoming) {
        String key = "euroNorm";
        if (!incoming.has(key)) return -1;
        return incoming.getInt(key);
    }

    private LocationDto getLocationDto(JSONObject location) {
        return new LocationDto.CameraLocationDtoBuilder()
                .withLatitude(location.getInt("lat"))
                .withLongitude(location.getInt("long"))
                .build();
    }

    private SegmentDto getSegmentDto(JSONObject incoming) {
        String key = "segment";
        if (!incoming.has(key))
            return null;
        JSONObject segment = incoming.getJSONObject(key);
        return new SegmentDto.SegmentDtoBuilder()
                .withConnectedCameraId(segment.getInt("connectedCameraId"))
                .withDistance(segment.getInt("distance"))
                .withSpeedLimit(segment.getInt("speedLimit"))
                .build();
    }
}

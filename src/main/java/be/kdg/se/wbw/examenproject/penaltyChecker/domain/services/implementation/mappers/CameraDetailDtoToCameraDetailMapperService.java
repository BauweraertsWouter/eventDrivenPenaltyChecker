package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation.mappers;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.CameraDetail;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.Location;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.Segment;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.api.TypeMapper;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.CameraDetailDto;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.LocationDto;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.SegmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
@Component
public class CameraDetailDtoToCameraDetailMapperService implements TypeMapper<CameraDetailDto, CameraDetail> {
    @Autowired
    private TypeMapper<SegmentDto, Segment> segmentMapper;
    @Autowired
    private TypeMapper<LocationDto, Location> locationMapper;

    @Override
    public CameraDetail map(CameraDetailDto incoming) {
        return new CameraDetail(incoming.getCameraId(),
                locationMapper.map(incoming.getLocation()),
                segmentMapper.map(incoming.getSegment()),
                incoming.getEuroNorm(),
                incoming.getRetrievingTimestamp());
    }
}

package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation.mappers;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.Segment;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.api.TypeMapper;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.SegmentDto;
import org.springframework.stereotype.Component;

@Component
public class SegmentDtoToSegmentMapper implements TypeMapper<SegmentDto, Segment> {
    @Override
    public Segment map(SegmentDto segmentDto) {
        return new Segment(segmentDto.getConnectedCameraId(),
                segmentDto.getDistance(),
                segmentDto.getSpeedLimit()
        );
    }
}

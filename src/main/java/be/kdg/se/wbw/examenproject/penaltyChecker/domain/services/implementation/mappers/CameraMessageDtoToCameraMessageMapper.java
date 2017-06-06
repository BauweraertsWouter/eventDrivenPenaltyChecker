package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation.mappers;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraMessage;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.api.TypeMapper;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.CameraMessageDto;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.exceptions.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class CameraMessageDtoToCameraMessageMapper implements TypeMapper<CameraMessageDto, CameraMessage> {
    private TypeMapper<Date, LocalDateTime> dateToLocalDateTimeMapperService;

    @Autowired
    public CameraMessageDtoToCameraMessageMapper(TypeMapper<Date, LocalDateTime> dateToLocalDateTimeMapperService) {
        this.dateToLocalDateTimeMapperService = dateToLocalDateTimeMapperService;
    }

    @Override
    public CameraMessage map(CameraMessageDto cameraMessageDto) {
        try {
            return new CameraMessage.CameraMessageBuilder()
                    .withLicensePlate(cameraMessageDto.getLicensePlate())
                    .withTimestamp(dateToLocalDateTimeMapperService.map(cameraMessageDto.getTimestamp()))
                    .withCameraId(cameraMessageDto.getCameraId())
                    .build();
        }catch (Exception e){
            if (e.getClass().equals(MappingException.class))
                throw e;
            throw new MappingException("Something went wrong while mapping CameraMessageDto to CameraMessage");
        }
    }
}

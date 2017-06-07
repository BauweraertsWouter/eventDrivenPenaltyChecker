package be.kdg.se.wbw.examenproject.penaltyChecker.adapters.implementation.mappers;


import be.kdg.se.wbw.examenproject.penaltyChecker.shared.api.TypeMapper;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.LicensePlateDetailDto;
import org.json.JSONObject;

import org.springframework.stereotype.Component;

@Component
public class JSONObjectToLicensePlateDetailDtoMapperService implements TypeMapper<JSONObject, LicensePlateDetailDto> {
    @Override
    public LicensePlateDetailDto map(JSONObject incoming) {
        return new LicensePlateDetailDto.LicensePlateDetailDtoBuilder()
                .withPlateId(incoming.getString("plateId"))
                .withNationalNumber(incoming.getString("nationalNumber"))
                .withEuroNorm(incoming.getInt("euroNumber"))
                .build();
    }
}

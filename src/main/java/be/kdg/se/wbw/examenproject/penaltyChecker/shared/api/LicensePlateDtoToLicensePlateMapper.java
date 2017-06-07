package be.kdg.se.wbw.examenproject.penaltyChecker.shared.api;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.LicensePlateDetails;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.LicensePlateDetailDto;
import org.springframework.stereotype.Component;

@Component
public class LicensePlateDtoToLicensePlateMapper implements TypeMapper<LicensePlateDetailDto, LicensePlateDetails> {
    @Override
    public LicensePlateDetails map(LicensePlateDetailDto licensePlateDetailDto) {
        return new LicensePlateDetails(
                licensePlateDetailDto.getPlateId(),
                licensePlateDetailDto.getNationalNumber(),
                licensePlateDetailDto.getEuroNorm()
        );
    }
}

package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation.mappers;

import be.kdg.se.wbw.examenproject.penaltyChecker.shared.api.TypeMapper;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.exceptions.MappingException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class DateToLocalDateTimeMapperService implements TypeMapper<Date, LocalDateTime> {
    @Override
    public LocalDateTime map(Date incoming) {
        try {
            return LocalDateTime.ofInstant(incoming.toInstant(), ZoneId.systemDefault());
        }catch (Exception e){
            throw new MappingException("Exception occurred while mapping Date to LocalDate");
        }
    }
}

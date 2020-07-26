package CalceTeam.Microservice.mappers;

import org.mapstruct.*;
import CalceTeam.Microservice.dtos.PatientDto;
import CalceTeam.Microservice.models.Patient;


@Mapper
public interface PatientMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePatientFromDto(PatientDto dto, @MappingTarget Patient patient);
}
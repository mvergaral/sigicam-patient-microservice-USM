package CalceTeam.Microservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import CalceTeam.Microservice.dtos.PatientDto;
import CalceTeam.Microservice.models.Patient;


@Mapper(componentModel = "spring")
public interface PatientMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updatePatientFromDto(PatientDto dto, @MappingTarget Patient patient);
}
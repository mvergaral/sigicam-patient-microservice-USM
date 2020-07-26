package CalceTeam.Microservice.services;


import CalceTeam.Microservice.models.Patient;
import CalceTeam.Microservice.daos.PatientRepository;
import CalceTeam.Microservice.dtos.PatientDto;
import CalceTeam.Microservice.mappers.PatientMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import static java.lang.System.out;


@Service("PatientService")
public class PatientServiceImpl{

    @Autowired
    @Qualifier("PatientRepository")
    private PatientRepository repository;
    private PatientMapper mapper;

    public boolean create(Patient patient){
        try{
            repository.save(patient);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean delete(long id){
        try{
            Patient patient = repository.findById(id);
            repository.delete(patient);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean update(PatientDto dto) {
        try{
            Patient patient = repository.findById(dto.getId());
            System.out.println(patient);
            System.out.println(dto);
            mapper.updatePatientFromDto(dto, patient);
            repository.save(patient);
            return true;
        } catch (Exception e){
            return false;
        }
        
    }

    public Patient findById(long id){
        try{
            return repository.findById(id);
        }catch(Exception e){
            return null;
        }
    }

    public Patient findByRun(int run){
        try{
            return repository.findByRun(run);
        }catch(Exception e){
            return null;
        }
    }
    
}



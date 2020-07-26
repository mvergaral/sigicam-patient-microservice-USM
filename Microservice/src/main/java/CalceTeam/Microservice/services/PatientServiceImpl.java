package CalceTeam.Microservice.services;


import CalceTeam.Microservice.models.Patient;
import CalceTeam.Microservice.daos.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service("PatientService")
public class PatientServiceImpl{

    @Autowired
    @Qualifier("PatientRepository")
    private PatientRepository repository;

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

    public boolean update(Patient patient){
        try{
            repository.save(patient);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Patient findById(long id){
        return repository.findById(id);
    }
    
}



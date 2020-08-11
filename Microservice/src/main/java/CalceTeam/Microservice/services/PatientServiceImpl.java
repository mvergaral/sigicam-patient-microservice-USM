package CalceTeam.Microservice.services;


import CalceTeam.Microservice.models.*;
import CalceTeam.Microservice.daos.*;
import CalceTeam.Microservice.dtos.PatientDto;
import CalceTeam.Microservice.mappers.PatientMapper;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import static java.lang.System.out;
import java.util.List;

@Service("PatientService")
public class PatientServiceImpl{

    @Autowired
    @Qualifier("PatientRepository")
    private PatientRepository repository;

    @Autowired
    private PatientMapper mapper;

    @Autowired
    @Qualifier("PatientBedRepository")
    private PatientBedRepository patientBedRepository;

    @Autowired
    @Qualifier("PatientCouchRepository")
    private PatientCouchRepository patientCouchRepository;

    @Autowired
    @Qualifier("BedRepository")
    private BedRepository bedRepository;

    @Autowired
    @Qualifier("CouchRepository")
    private CouchRepository couchRepository;

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

    public boolean assignBedtoPatient(long id_bed, long id_patient){
        try{
            PatientBed bedToPatient = new PatientBed();
            bedToPatient.setPatientbed(repository.findById(id_patient));
            bedToPatient.setBed(bedRepository.findById(id_bed));
            patientBedRepository.save(bedToPatient);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public boolean assignCouchtoPatient(long id_couch, long id_patient){
        try{
            PatientCouch couchToPatient = new PatientCouch();
            couchToPatient.setPatientcouch(repository.findById(id_patient));
            couchToPatient.setCouch(couchRepository.findById(id_couch));
            patientCouchRepository.save(couchToPatient);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public List<Patient> getAllPatients() {
        try {
            return repository.getAllPatients();
        } catch (Exception e) {
            return null;
        }
    }
    
}



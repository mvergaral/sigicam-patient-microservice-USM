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

    public boolean update(long id_patient, PatientDto dto) {
        try{
            dto.setId(id_patient);
            Patient patient = repository.findById(id_patient);
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

    public Patient findByRun(String run){
        try{
            return repository.findByRun(run);
        }catch(Exception e){
            return null;
        }
    }

    public int assignBedtoPatient(long id_bed, long id_patient){
        try{
            Long idPatientBed = patientBedRepository.getLastBedOfPatient(id_patient);
            if(idPatientBed != null ){
                PatientBed patientBed = patientBedRepository.findById(idPatientBed);
                if(patientBed.getStatus().equals("INACTIVE")){
                    PatientBed bedToPatient = new PatientBed();
                    bedToPatient.setPatientbed(repository.findById(id_patient));
                    bedToPatient.setBed(bedRepository.findById(id_bed));
                    bedToPatient.setStatus("ACTIVE");
                    patientBedRepository.save(bedToPatient);
                    return 0;
                }
                return 1;
            }
            PatientBed bedToPatient = new PatientBed();
            bedToPatient.setPatientbed(repository.findById(id_patient));
            bedToPatient.setBed(bedRepository.findById(id_bed));
            bedToPatient.setStatus("ACTIVE");
            patientBedRepository.save(bedToPatient);
            return 0;
        }catch(Exception e){
            return 2;
        }

        
    }

    public int assignCouchtoPatient(long id_couch, long id_patient){
        try{
            Long idPatientCouch = patientCouchRepository.getLastCouchOfPatient(id_patient);
            if(idPatientCouch != null ){
                PatientCouch patientCouch = patientCouchRepository.findById(idPatientCouch);
                if(patientCouch.getStatus().equals("INACTIVE")){
                    PatientCouch couchToPatient = new PatientCouch();
                    couchToPatient.setPatientcouch(repository.findById(id_patient));
                    couchToPatient.setCouch(couchRepository.findById(id_couch));
                    couchToPatient.setStatus("ACTIVE");
                    patientCouchRepository.save(couchToPatient);
                    return 0;
                }
                return 1;
            }
            PatientCouch couchToPatient = new PatientCouch();
            couchToPatient.setPatientcouch(repository.findById(id_patient));
            couchToPatient.setCouch(couchRepository.findById(id_couch));
            couchToPatient.setStatus("ACTIVE");
            patientCouchRepository.save(couchToPatient);
            return 0;
            
        }catch(Exception e){
            return 2;
        }
    }
    
    public List<Patient> getAllPatients() {
        try {
            return repository.getAllPatients();
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean deletePatient(long id){
        try{
            repository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public int deallocateBed(long id_patient){
        try{
            Long idPatientBed = patientBedRepository.getLastBedOfPatient(id_patient);
            if(idPatientBed != null ){
                PatientBed patientBed = patientBedRepository.findById(idPatientBed);
                patientBed.setStatus("INACTIVE");
                patientBedRepository.save(patientBed);
                return 0;
            }
            return 1;
        }catch(Exception e){
            return 2;
        }
    }

    public int deallocateCouch(long id_patient){
        try{
            Long idPatientCouch = patientCouchRepository.getLastCouchOfPatient(id_patient);
            if(idPatientCouch != null ){
                PatientCouch patientCouch = patientCouchRepository.findById(idPatientCouch);
                patientCouch.setStatus("INACTIVE");
                patientCouchRepository.save(patientCouch);
                return 0;
            }
            return 1;
        }catch(Exception e){
            return 2;
        }
    }

    public Long getAssignedBed(long id_patient){
        return patientBedRepository.getActiveBedIdOfPatient(id_patient);
    }

    public Long getAssignedCouch(long id_patient){
        return patientCouchRepository.getActiveCouchIdOfPatient(id_patient);
    }
}



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

    public boolean delete(Long id){
        try{
            Patient patient = repository.findById(id);
            repository.delete(patient);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean update(Long id_patient, PatientDto dto) {
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

    public Patient findById(Long id){
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

    public int assignBedtoPatient(Long id_bed, Long id_patient){
        try{
            Long idPatientBed = patientBedRepository.getLastBedOfPatient(id_patient);
            if(idPatientBed != null ){
                PatientBed patientBed = patientBedRepository.findById(idPatientBed);
                if(patientBed.getStatus().equals("INACTIVE")){
                    PatientBed bedToPatient = new PatientBed();
                    bedToPatient.setPatient(repository.findById(id_patient));
                    Bed bed = bedRepository.findById(id_bed);
                    if(bed == null){
                        bed = new Bed();
                        bed.setId(id_bed);
                        bedRepository.save(bed);
                    }
                    bedToPatient.setBed(bed);
                    bedToPatient.setStatus("ACTIVE");
                    patientBedRepository.save(bedToPatient);
                    return 0;
                }
                return 1;
            }
            PatientBed bedToPatient = new PatientBed();
            bedToPatient.setPatient(repository.findById(id_patient));
            Bed bed = bedRepository.findById(id_bed);
            if(bed == null){
                bed = new Bed();
                bed.setId(id_bed);
                bedRepository.save(bed);
            }
            bedToPatient.setBed(bed);
            bedToPatient.setStatus("ACTIVE");
            patientBedRepository.save(bedToPatient);
            return 0;
        }catch(Exception e){
            return 2;
        }

        
    }

    public int assignCouchtoPatient(Long id_couch, Long id_patient){
        try{
            Long idPatientCouch = patientCouchRepository.getLastCouchOfPatient(id_patient);
            if(idPatientCouch != null ){
                PatientCouch patientCouch = patientCouchRepository.findById(idPatientCouch);
                if(patientCouch.getStatus().equals("INACTIVE")){
                    PatientCouch couchToPatient = new PatientCouch();
                    couchToPatient.setPatient(repository.findById(id_patient));
                    Couch couch = couchRepository.findById(id_couch);
                    if(couch == null){
                        couch = new Couch();
                        couch.setId(id_couch);
                        couchRepository.save(couch);
                    }
                    couchToPatient.setCouch(couch);
                    couchToPatient.setStatus("ACTIVE");
                    patientCouchRepository.save(couchToPatient);
                    return 0;
                }
                return 1;
            }
            PatientCouch couchToPatient = new PatientCouch();
            couchToPatient.setPatient(repository.findById(id_patient));
            Couch couch = couchRepository.findById(id_couch);
            if(couch == null){
                couch = new Couch();
                couch.setId(id_couch);
                couchRepository.save(couch);
            }
            couchToPatient.setCouch(couch);
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
    
    public boolean deletePatient(Long id){
        try{
            repository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public int deallocateBed(Long id_patient){
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

    public int deallocateCouch(Long id_patient){
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

    public Long getAssignedBed(Long id_patient){
        return patientBedRepository.getActiveBedIdOfPatient(id_patient);
    }

    public Long getAssignedCouch(Long id_patient){
        return patientCouchRepository.getActiveCouchIdOfPatient(id_patient);
    }
}



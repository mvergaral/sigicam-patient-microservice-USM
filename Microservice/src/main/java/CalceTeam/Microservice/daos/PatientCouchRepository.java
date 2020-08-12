package CalceTeam.Microservice.daos;

import java.io.Serializable;

import CalceTeam.Microservice.models.PatientCouch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository("PatientCouchRepository")
public interface PatientCouchRepository extends JpaRepository<PatientCouch , Serializable> {

  public abstract PatientCouch findById(Long id);
  public abstract PatientCouch findTopByOrderByIdDesc();

  @Query(value = "SELECT MAX(id) from patient_couch where patient_id = ?1", nativeQuery = true)
  public Long getLastCouchOfPatient(Long id_patient);  

  @Query(value = "SELECT couch_id from patient_couch where id = (select MAX(id) from patient_couch where patient_id = ?1 AND status = 'ACTIVE') ", nativeQuery = true)
  public Long getActiveCouchIdOfPatient(Long id_patient);

}
package CalceTeam.Microservice.daos;

import java.io.Serializable;

import CalceTeam.Microservice.models.PatientBed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository("PatientBedRepository")
public interface PatientBedRepository extends JpaRepository<PatientBed , Serializable> {

  public abstract PatientBed findById(Long id);

  @Query(value = "SELECT MAX(patient_bed_id) from patient_bed where patient_id = ?1", nativeQuery = true)
  public Long getLastBedOfPatient(long id_patient);  

}
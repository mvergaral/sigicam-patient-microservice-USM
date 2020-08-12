package CalceTeam.Microservice.daos;

import java.io.Serializable;

import CalceTeam.Microservice.models.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository("PatientRepository")
public interface PatientRepository extends JpaRepository<Patient , Serializable> {

  public abstract Patient findById(long id);
  public abstract Patient findByRun(String run);

  @Query(value = "SELECT * from patient", nativeQuery = true)
  public List<Patient> getAllPatients();  

}
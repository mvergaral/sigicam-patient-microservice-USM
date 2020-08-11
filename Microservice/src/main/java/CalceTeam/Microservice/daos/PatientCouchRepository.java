package CalceTeam.Microservice.daos;

import java.io.Serializable;

import CalceTeam.Microservice.models.PatientCouch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("PatientCouchRepository")
public interface PatientCouchRepository extends JpaRepository<PatientCouch , Serializable> {

  public abstract PatientCouchRepository findById(long id);

}
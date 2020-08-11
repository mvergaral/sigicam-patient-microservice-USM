package CalceTeam.Microservice.daos;

import java.io.Serializable;

import CalceTeam.Microservice.models.PatientBed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("PatientBedRepository")
public interface PatientBedRepository extends JpaRepository<PatientBed , Serializable> {

  public abstract PatientBedRepository findById(long id);

}
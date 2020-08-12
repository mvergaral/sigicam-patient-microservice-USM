package CalceTeam.Microservice.daos;

import java.io.Serializable;

import CalceTeam.Microservice.models.Bed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("BedRepository")
public interface BedRepository extends JpaRepository<Bed , Serializable> {

  public abstract Bed findById(Long id);

}
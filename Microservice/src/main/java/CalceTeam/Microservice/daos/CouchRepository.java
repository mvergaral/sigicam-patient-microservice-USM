package CalceTeam.Microservice.daos;

import java.io.Serializable;

import CalceTeam.Microservice.models.Couch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("CouchRepository")
public interface CouchRepository extends JpaRepository<Couch , Serializable> {

  public abstract Couch findById(Long id);

}
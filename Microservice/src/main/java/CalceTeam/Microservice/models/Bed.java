package CalceTeam.Microservice.models;

import java.util.Set;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.CascadeType;
import CalceTeam.Microservice.models.Patient;

@Entity
@Table(name = "bed")
public class Bed {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "bed_id", nullable = false, unique = true)
  private long id;

  @ManyToMany(mappedBy = "beds")
  private Set<Patient> Patients = new HashSet<>();

  // standard constructors/getters/setters
}
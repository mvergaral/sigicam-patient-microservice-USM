package CalceTeam.Microservice.models;

import java.util.Objects;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "couch")
public class Couch {
  @Id
  @Column(name = "couch_id", nullable = false, unique = true)
  private Long id;

  @JsonIgnore
  @OneToMany(mappedBy = "couch",cascade = CascadeType.REMOVE)
  Set<PatientCouch> patients;


  public Couch() {
  }

  public Couch(Long id, Set<PatientCouch> patients) {
    this.id = id;
    this.patients = patients;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Set<PatientCouch> getPatients() {
    return this.patients;
  }

  public void setPatients(Set<PatientCouch> patients) {
    this.patients = patients;
  }

  public Couch id(Long id) {
    this.id = id;
    return this;
  }

  public Couch patients(Set<PatientCouch> patients) {
    this.patients = patients;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Couch)) {
            return false;
        }
        Couch couch = (Couch) o;
        return id == couch.id && Objects.equals(patients, couch.patients);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, patients);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", patients='" + getPatients() + "'" +
      "}";
  }


}
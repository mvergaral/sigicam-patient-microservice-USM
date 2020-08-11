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
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "couch")
public class Couch {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "couch_id", nullable = false, unique = true)
  private long id;

  @JsonIgnore
  @ManyToMany(mappedBy = "couchs")
  private Set<Patient> Patients = new HashSet<>(); 


  public Couch() {
  }

  public Couch(long id, Set<Patient> Patients) {
    this.id = id;
    this.Patients = Patients;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Set<Patient> getPatients() {
    return this.Patients;
  }

  public void setPatients(Set<Patient> Patients) {
    this.Patients = Patients;
  }

  public Couch id(long id) {
    this.id = id;
    return this;
  }

  public Couch Patients(Set<Patient> Patients) {
    this.Patients = Patients;
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
        return id == couch.id && Objects.equals(Patients, couch.Patients);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, Patients);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", Patients='" + getPatients() + "'" +
      "}";
  }

}
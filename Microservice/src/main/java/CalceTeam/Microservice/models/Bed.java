package CalceTeam.Microservice.models;

import java.util.Objects;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.OneToMany;

@Entity
@Table(name = "bed")
public class Bed {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "bed_id", nullable = false, unique = true)
  private long id;

  @JsonIgnore
  @OneToMany(mappedBy = "bed",cascade = CascadeType.REMOVE)
  Set<PatientBed> patients;


  public Bed() {
  }

  public Bed(long id, Set<PatientBed> patients) {
    this.id = id;
    this.patients = patients;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Set<PatientBed> getPatients() {
    return this.patients;
  }

  public void setPatients(Set<PatientBed> patients) {
    this.patients = patients;
  }

  public Bed id(long id) {
    this.id = id;
    return this;
  }

  public Bed patients(Set<PatientBed> patients) {
    this.patients = patients;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Bed)) {
            return false;
        }
        Bed bed = (Bed) o;
        return id == bed.id && Objects.equals(patients, bed.patients);
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
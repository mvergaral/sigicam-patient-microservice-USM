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
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.CascadeType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import CalceTeam.Microservice.models.*;

@Entity
@Table(name = "patientCouch")
public class PatientCouch {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "patientBed_id", nullable = false, unique = true)
  private long id;

  @ManyToOne()
  @JoinColumn(name = "patient_id", nullable = false)
  Patient patient;

  @ManyToOne()
  @JoinColumn(name = "couch_id", nullable = false)
  Couch couch;

  @Column(name = "status")
  String status;



  public PatientCouch() {
  }

  public PatientCouch(long id, Patient patient, Couch couch, String status) {
    this.id = id;
    this.patient = patient;
    this.couch = couch;
    this.status = status;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Patient getPatient() {
    return this.patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  public Couch getCouch() {
    return this.couch;
  }

  public void setCouch(Couch couch) {
    this.couch = couch;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public PatientCouch id(long id) {
    this.id = id;
    return this;
  }

  public PatientCouch patient(Patient patient) {
    this.patient = patient;
    return this;
  }

  public PatientCouch couch(Couch couch) {
    this.couch = couch;
    return this;
  }

  public PatientCouch status(String status) {
    this.status = status;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PatientCouch)) {
            return false;
        }
        PatientCouch patientCouch = (PatientCouch) o;
        return id == patientCouch.id && Objects.equals(patient, patientCouch.patient) && Objects.equals(couch, patientCouch.couch) && Objects.equals(status, patientCouch.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, patient, couch, status);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", patient='" + getPatient() + "'" +
      ", couch='" + getCouch() + "'" +
      ", status='" + getStatus() + "'" +
      "}";
  }

  
}
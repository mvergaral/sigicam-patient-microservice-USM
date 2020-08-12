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
@Table(name = "patientBed")
public class PatientBed {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "patientBed_id", nullable = false, unique = true)
  private long id;

  @ManyToOne
  @JoinColumn(name = "patient_id", nullable = false)
  Patient patientbed;

  @ManyToOne
  @JoinColumn(name = "bed_id", nullable = false)
  Bed bed;

  @Column(name = "status", nullable = false)
  String status;



  public PatientBed() {
  }

  public PatientBed(long id, Patient patientbed, Bed bed, String status) {
    this.id = id;
    this.patientbed = patientbed;
    this.bed = bed;
    this.status = status;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Patient getPatientbed() {
    return this.patientbed;
  }

  public void setPatientbed(Patient patientbed) {
    this.patientbed = patientbed;
  }

  public Bed getBed() {
    return this.bed;
  }

  public void setBed(Bed bed) {
    this.bed = bed;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public PatientBed id(long id) {
    this.id = id;
    return this;
  }

  public PatientBed patientbed(Patient patientbed) {
    this.patientbed = patientbed;
    return this;
  }

  public PatientBed bed(Bed bed) {
    this.bed = bed;
    return this;
  }

  public PatientBed status(String status) {
    this.status = status;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PatientBed)) {
            return false;
        }
        PatientBed patientBed = (PatientBed) o;
        return id == patientBed.id && Objects.equals(patientbed, patientBed.patientbed) && Objects.equals(bed, patientBed.bed) && Objects.equals(status, patientBed.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, patientbed, bed, status);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", patientbed='" + getPatientbed() + "'" +
      ", bed='" + getBed() + "'" +
      ", status='" + getStatus() + "'" +
      "}";
  }
  
  
}
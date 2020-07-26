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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.CascadeType;
import CalceTeam.Microservice.models.Bed;
import CalceTeam.Microservice.models.Couch;

@Entity
@Table(name = "patient")
public class Patient {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "patient_id", nullable = false, unique = true)
  private long id;

  @Column(name = "run", nullable = false, unique = true)
  private int run;

  @Column(name = "dv", nullable = false)
  private int dv;

  @Column(name = "name", nullable = false)
  String name;

  @Column(name = "last_name", nullable = false)
  String last_name;

  @Column(name = "gender", nullable = false)
  String gender;

  @Column(name = "address", nullable = false)
  String address;

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(name = "patient_bed", joinColumns = { @JoinColumn(name = "patient_id") }, inverseJoinColumns = {
      @JoinColumn(name = "bed_id") })
  Set<Bed> beds = new HashSet<>();

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(name = "patient_couch", joinColumns = { @JoinColumn(name = "patient_id") }, inverseJoinColumns = {
      @JoinColumn(name = "couch_id") })
  Set<Couch> couchs = new HashSet<>();

  // standard constructor/getters/setters

  public Patient() {
  }

  public Patient(long id, int run, int dv, String name, String last_name, String gender, String address, Set<Bed> beds, Set<Couch> couchs) {
    this.id = id;
    this.run = run;
    this.dv = dv;
    this.name = name;
    this.last_name = last_name;
    this.gender = gender;
    this.address = address;
    this.beds = beds;
    this.couchs = couchs;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getRun() {
    return this.run;
  }

  public void setRun(int run) {
    this.run = run;
  }

  public int getDv() {
    return this.dv;
  }

  public void setDv(int dv) {
    this.dv = dv;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLast_name() {
    return this.last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getGender() {
    return this.gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Set<Bed> getBeds() {
    return this.beds;
  }

  public void setBeds(Set<Bed> beds) {
    this.beds = beds;
  }

  public Set<Couch> getCouchs() {
    return this.couchs;
  }

  public void setCouchs(Set<Couch> couchs) {
    this.couchs = couchs;
  }

  public Patient id(long id) {
    this.id = id;
    return this;
  }

  public Patient run(int run) {
    this.run = run;
    return this;
  }

  public Patient dv(int dv) {
    this.dv = dv;
    return this;
  }

  public Patient name(String name) {
    this.name = name;
    return this;
  }

  public Patient last_name(String last_name) {
    this.last_name = last_name;
    return this;
  }

  public Patient gender(String gender) {
    this.gender = gender;
    return this;
  }

  public Patient address(String address) {
    this.address = address;
    return this;
  }

  public Patient beds(Set<Bed> beds) {
    this.beds = beds;
    return this;
  }

  public Patient couchs(Set<Couch> couchs) {
    this.couchs = couchs;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Patient)) {
            return false;
        }
        Patient patient = (Patient) o;
        return id == patient.id && run == patient.run && dv == patient.dv && Objects.equals(name, patient.name) && Objects.equals(last_name, patient.last_name) && Objects.equals(gender, patient.gender) && Objects.equals(address, patient.address) && Objects.equals(beds, patient.beds) && Objects.equals(couchs, patient.couchs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, run, dv, name, last_name, gender, address, beds, couchs);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", run='" + getRun() + "'" +
      ", dv='" + getDv() + "'" +
      ", name='" + getName() + "'" +
      ", last_name='" + getLast_name() + "'" +
      ", gender='" + getGender() + "'" +
      ", address='" + getAddress() + "'" +
      ", beds='" + getBeds() + "'" +
      ", couchs='" + getCouchs() + "'" +
      "}";
  }
}
package CalceTeam.Microservice.dtos;

import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

import CalceTeam.Microservice.models.*;


public class PatientDto {
  private Long id;
  private String run;
  String name;
  String last_name;
  String gender;
  String address;
  Set<PatientBed> beds;
  Set<PatientCouch> couchs;


  public PatientDto() {
  }

  public PatientDto(Long id, String run, String name, String last_name, String gender, String address, Set<PatientBed> beds, Set<PatientCouch> couchs) {
    this.id = id;
    this.run = run;
    this.name = name;
    this.last_name = last_name;
    this.gender = gender;
    this.address = address;
    this.beds = beds;
    this.couchs = couchs;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRun() {
    return this.run;
  }

  public void setRun(String run) {
    this.run = run;
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

  public Set<PatientBed> getBeds() {
    return this.beds;
  }

  public void setBeds(Set<PatientBed> beds) {
    this.beds = beds;
  }

  public Set<PatientCouch> getCouchs() {
    return this.couchs;
  }

  public void setCouchs(Set<PatientCouch> couchs) {
    this.couchs = couchs;
  }

  public PatientDto id(Long id) {
    this.id = id;
    return this;
  }

  public PatientDto run(String run) {
    this.run = run;
    return this;
  }

  public PatientDto name(String name) {
    this.name = name;
    return this;
  }

  public PatientDto last_name(String last_name) {
    this.last_name = last_name;
    return this;
  }

  public PatientDto gender(String gender) {
    this.gender = gender;
    return this;
  }

  public PatientDto address(String address) {
    this.address = address;
    return this;
  }

  public PatientDto beds(Set<PatientBed> beds) {
    this.beds = beds;
    return this;
  }

  public PatientDto couchs(Set<PatientCouch> couchs) {
    this.couchs = couchs;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PatientDto)) {
            return false;
        }
        PatientDto patientDto = (PatientDto) o;
        return id == patientDto.id && Objects.equals(run, patientDto.run) && Objects.equals(name, patientDto.name) && Objects.equals(last_name, patientDto.last_name) && Objects.equals(gender, patientDto.gender) && Objects.equals(address, patientDto.address) && Objects.equals(beds, patientDto.beds) && Objects.equals(couchs, patientDto.couchs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, run, name, last_name, gender, address, beds, couchs);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", run='" + getRun() + "'" +
      ", name='" + getName() + "'" +
      ", last_name='" + getLast_name() + "'" +
      ", gender='" + getGender() + "'" +
      ", address='" + getAddress() + "'" +
      ", beds='" + getBeds() + "'" +
      ", couchs='" + getCouchs() + "'" +
      "}";
  }

 
}
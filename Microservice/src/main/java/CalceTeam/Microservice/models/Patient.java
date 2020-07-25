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
import CalceTeam.Microservice.models.Bed;

@Entity
@Table(name = "patient")
public class Patient {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "patient_id", nullable = false, unique = true)
  private long id;

  @Column(name = "run", nullable = false)
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
  Set<Bed> couchs = new HashSet<>();

  // standard constructor/getters/setters
}
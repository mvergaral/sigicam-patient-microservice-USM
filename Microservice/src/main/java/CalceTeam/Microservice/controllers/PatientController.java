package CalceTeam.Microservice.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import CalceTeam.Microservice.models.*;
import CalceTeam.Microservice.services.PatientServiceImpl; 
import CalceTeam.Microservice.dtos.PatientDto;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/patient")
public class PatientController {
  @Autowired
  @Qualifier("PatientService")
  PatientServiceImpl service;

  @PostMapping("/new")
    public ResponseEntity<Void> createPatient(@RequestBody @Valid Patient patient){
      if(service.create(patient)){
        return new ResponseEntity<>(HttpStatus.CREATED);
      }
      else{
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

  @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") long id){
      Patient patient = service.findById(id);
      if (patient != null){
        return new ResponseEntity<>(patient, HttpStatus.FOUND);
      }
      else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

    }

  @GetMapping("/run/{run}")
    public ResponseEntity<Patient> getPatientByRun(@PathVariable("run") int run){
      Patient patient = service.findByRun(run);
      if (patient != null){
        return new ResponseEntity<>(patient, HttpStatus.OK);
      }
      else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

    }

  @PutMapping("/update")
    public ResponseEntity<Void> updatePatient(@RequestBody @Valid PatientDto patient){
      if(service.update(patient)){
        return new ResponseEntity<>(HttpStatus.CREATED);
      }
      else{
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

<<<<<<< Updated upstream
  @GetMapping("/{id}/camas")
    public ResponseEntity<Set<Bed>> getPatientBeds(@PathVariable("id") long id){
      Patient patient = service.findById(id);
      if (patient != null){
        return new ResponseEntity<>(patient.getBeds(), HttpStatus.FOUND);
      }
      else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

  @GetMapping("/{id}/sillones")
    public ResponseEntity<Set<Couch>> getPatientCouch(@PathVariable("id") long id){
      Patient patient = service.findById(id);
      if (patient != null){
        return new ResponseEntity<>(patient.getCouchs(), HttpStatus.FOUND);
      }
      else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAllPatients() {
      List<Patient> patients = service.getAllPatients();
      if (patients.size() != 0) {
        return new ResponseEntity<>(patients, HttpStatus.FOUND);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }
=======
  @PutMapping("/{id}/assignBed")
    public ResponseEntity<Void> assignBedtoPatient(@PathVariable("id") long id, @Param("id_bed") long id_bed ){
      if(service.assignBedtoPatient(id_bed, id)){
        return new ResponseEntity<>(HttpStatus.CREATED);
      }
      else{
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

  @PutMapping("/{id}/assignCouch")
    public ResponseEntity<Void> assignCouchtoPatient(@PathVariable long id, @RequestHeader  long id_couch ){
      if(service.assignCouchtoPatient(id_couch, id)){
        return new ResponseEntity<>(HttpStatus.CREATED);
      }
      else{
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    
>>>>>>> Stashed changes
}
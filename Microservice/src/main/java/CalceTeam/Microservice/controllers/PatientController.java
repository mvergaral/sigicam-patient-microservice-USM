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
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
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
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Long id){
      Patient patient = service.findById(id);
      if (patient != null){
        return new ResponseEntity<>(patient, HttpStatus.FOUND);
      }
      else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

    }

  @GetMapping("/findByRun")
    public ResponseEntity<Patient> getPatientByRun(@Param("run") String run){
      Patient patient = service.findByRun(run);
      if (patient != null){
        return new ResponseEntity<>(patient, HttpStatus.FOUND);
      }
      else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

    }

  @PutMapping("/{id}/update")
    public ResponseEntity<Void> updatePatient(@PathVariable Long id,@RequestBody @Valid PatientDto patient){
      if(service.update(id,patient)){
        return new ResponseEntity<>(HttpStatus.CREATED);
      }
      else{
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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

  @PutMapping("/{id}/assignBed")
    public ResponseEntity<Void> assignBedtoPatient(@PathVariable("id") Long id, @Param("id_bed") Long id_bed ){
      int response = service.assignBedtoPatient(id_bed, id); 
      if(response == 0){
        return new ResponseEntity<>(HttpStatus.CREATED);
      }
      else if(response == 1){
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
      }
      else{
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

  @PutMapping("/{id}/assignCouch")
    public ResponseEntity<Void> assignCouchtoPatient(@PathVariable Long id, @Param("id_couch") Long id_couch ){
      int response = service.assignCouchtoPatient(id_couch, id); 
      if(response == 0){
        return new ResponseEntity<>(HttpStatus.CREATED);
      }
      else if(response == 1){
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
      }
      else{
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    
  @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id){
      if(service.deletePatient(id)){
        return new ResponseEntity<>(HttpStatus.OK);
      }
      else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

  @PutMapping("/{id}/deallocateBed")
    public ResponseEntity<Void> deallocateBedofPatient(@PathVariable("id") Long id){
      int response = service.deallocateBed(id); 
      if(response == 0){
        return new ResponseEntity<>(HttpStatus.CREATED);
      }
      else if(response == 1){
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
      }
      else{
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

  @PutMapping("/{id}/deallocateCouch")
    public ResponseEntity<Void> deallocateCouchOfPatient(@PathVariable Long id){
      int response = service.deallocateCouch(id); 
      if(response == 0){
        return new ResponseEntity<>(HttpStatus.CREATED);
      }
      else if(response == 1){
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
      }
      else{
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

  @GetMapping("/{id}/activeBed")
    public ResponseEntity<Long> getAssignedBed(@PathVariable Long id){
      Long response = service.getAssignedBed(id);
      if(response != null){
        return new ResponseEntity<>(response, HttpStatus.FOUND);
      }

      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

  @GetMapping("/{id}/activeCouch")
    public ResponseEntity<Long> getAssignedCouch(@PathVariable Long id){
      Long response = service.getAssignedCouch(id);
      if(response != null){
        return new ResponseEntity<>(response, HttpStatus.FOUND);
      }

      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
  
}
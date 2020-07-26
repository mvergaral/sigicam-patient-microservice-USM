package CalceTeam.Microservice.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import CalceTeam.Microservice.models.*;
import CalceTeam.Microservice.services.PatientServiceImpl; 
import CalceTeam.Microservice.dtos.PatientDto;
import java.util.List;

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
}
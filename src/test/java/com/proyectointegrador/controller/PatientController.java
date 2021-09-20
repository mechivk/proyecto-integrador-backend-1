package com.proyectointegrador.controller;

import com.proyectointegrador.entity.Patient;
import com.proyectointegrador.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/register")
    public ResponseEntity<Patient> register(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.register(patient));
    }

    @GetMapping()
    public ResponseEntity<Optional<Patient>> buscar(@Param("dni") Integer dni) {
        ResponseEntity<Optional<Patient>> response = null;
        if (patientService.find(dni).isPresent()){
            response=ResponseEntity.ok(patientService.find(dni));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @PutMapping("/modify")
    public ResponseEntity<Patient> modify(@RequestBody Patient patient){
        ResponseEntity<Patient> response = null;
        Patient patient1 = patientService.find(patient.getDni()).get();
        patient1.setUpdateData(patient);
        if(patient1 != null) {
            response=ResponseEntity.ok(patientService.update(patient1));

        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(@Param("dni") Integer dni){
        ResponseEntity<String> response = null;
        if (patientService.find(dni) != null) {
            System.out.println(dni);
            patientService.delete(dni);
            response = ResponseEntity.ok("Deleted");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }



}
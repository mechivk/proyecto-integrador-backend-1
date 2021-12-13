package com.proyectointegrador.controller;

import com.proyectointegrador.entity.Odontologist;
import com.proyectointegrador.entity.Patient;
import com.proyectointegrador.service.PatientService;
import com.proyectointegrador.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    Logger log = Logger.getLogger(OdontologistController.class);

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<Patient> register(Patient patient, @RequestHeader("Authorization") String token) {
        ResponseEntity<Patient> response =null;
        if(userService.isAdminUser(token)) {
            if (patient != null) {
                response = ResponseEntity.ok(patientService.register(patient));
                log.info(patient.toString());
            } else {
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                log.error("Ocurrió un error al añadir un paciente");
            }
        } else {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            log.error("No tiene autorización para realizar esta acción");
        }
        return  response;
    }

    @GetMapping("/")
    public ResponseEntity<?> listAll(){
        ResponseEntity<List<Patient>> response = null;
        if(patientService.list() != null){
            response = ResponseEntity.ok(patientService.list());
        }else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;

    }

    @GetMapping("/{dni}")
    public ResponseEntity<Optional<Patient>> find(@PathVariable("dni") Integer dni, @RequestHeader("Authorization") String token) {
        ResponseEntity<Optional<Patient>> response = null;
        if(userService.isAdminUser(token)) {
            if (patientService.find(dni).isPresent()) {
                response = ResponseEntity.ok(patientService.find(dni));
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            log.error("No tiene autorización para realizar esta acción");
        }
        return response;
    }

    @PutMapping("/modify")
    public ResponseEntity<Patient> modify(Patient patient, @RequestHeader("Authorization") String token){
        ResponseEntity<Patient> response = null;
        if(userService.isAdminUser(token)) {
            Patient patient1 = patientService.find(patient.getDni()).get();
            System.out.println(patient1);
            patient1.setUpdateData(patient);
            if (patient1 != null) {
                response = ResponseEntity.ok(patientService.update(patient1));

            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            log.error("No tiene autorización para realizar esta acción");
        }
        return response;
    }

    @DeleteMapping("/delete/{dni}")
    public ResponseEntity<String> delete(@PathVariable("dni") Integer dni, @RequestHeader("Authorization") String token){
        ResponseEntity<String> response = null;
        if(userService.isAdminUser(token)) {
            if (patientService.find(dni) != null) {
                System.out.println(dni);
                patientService.delete(dni);
                response = ResponseEntity.ok("Deleted");
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            log.error("No tiene autorización para realizar esta acción");
        }

        return response;
    }



}
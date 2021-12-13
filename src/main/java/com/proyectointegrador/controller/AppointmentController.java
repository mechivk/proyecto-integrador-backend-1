package com.proyectointegrador.controller;

import com.proyectointegrador.entity.*;
import com.proyectointegrador.service.AppointmentService;
import com.proyectointegrador.service.OdontologistService;
import com.proyectointegrador.service.PatientService;
import com.proyectointegrador.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    Logger log = Logger.getLogger(AppointmentController.class);

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private OdontologistService odontologistService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;


    @PostMapping(path = "/add")
    public ResponseEntity<Appointment> add(@RequestParam("dni") Integer dni, @RequestParam("licenseNumber") Integer licenseNumber, @RequestParam("date") String date, @RequestParam("description") String description, @RequestHeader("Authorization") String token) {
        ResponseEntity<Appointment> response =null;
            if(userService.isAdminUser(token)){
                Appointment appointment = new Appointment();
                LocalDateTime dateAndTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                appointment.setDate(dateAndTime);

                if (appointment.getDate() != null){
                    Patient patient = patientService.find(dni).get();
                    appointment.setPatient(patient);
                    Odontologist odontologist = odontologistService.findbyLicenseNumber(licenseNumber).get();
                    appointment.setOdontologist(odontologist);
                    response = ResponseEntity.ok(appointmentService.add(appointment));
                    appointment.setDescription(description);
                    log.info(appointment.toString());
                }else {
                    response=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                    log.error("Ocurrió un error al añadir un turno");
                }
        } else {
                response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                log.error("No tiene autorización para realizar esta acción");
        }
        return  response;
    }

    @GetMapping("/")
    public ResponseEntity<List<Appointment>> listAll(){
        ResponseEntity<List<Appointment>> response = null;
        if(appointmentService.list() != null){
            response = ResponseEntity.ok(appointmentService.list());
        }else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;

    }

    //Búsqueda por dni del paciente
    @GetMapping("/{dni}")
    public ResponseEntity<Optional<List<Appointment>>> find(@PathVariable("dni") Integer dni) {
        ResponseEntity<Optional<List<Appointment>>> response = null;
        if (patientService.find(dni).isPresent()){
            appointmentService.findByPatient(patientService.find(dni).get());
            response=ResponseEntity.ok(appointmentService.findByPatient(patientService.find(dni).get()));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id, @RequestHeader("Authorization") String token){
        ResponseEntity<String> response = null;
        if(userService.isAdminUser(token)) {
            System.out.println(appointmentService.findById(id));
            if (appointmentService.findById(id).isPresent()) {
                appointmentService.delete(appointmentService.findById(id).get());
                response = ResponseEntity.ok("Deleted");
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                log.error("Ocurrió un error al añadir un turno");
            }
        } else {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            log.error("No tiene autorización para realizar esta acción");
        }
        return response;
    }




}

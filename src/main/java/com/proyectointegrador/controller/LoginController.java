package com.proyectointegrador.controller;

import com.proyectointegrador.entity.Patient;
import com.proyectointegrador.entity.User;
import com.proyectointegrador.repository.UserRepository;
import com.proyectointegrador.service.AppointmentService;
import com.proyectointegrador.service.PatientService;
import com.proyectointegrador.service.UserService;
import org.apache.coyote.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {
    Logger log = Logger.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @PostMapping("/")
    public ResponseEntity<List<?>> login(User user){
        ResponseEntity<List<?>> response = null;
        if(userService.findByUsername(user.getUsername()).isPresent()){
            User user1 = userService.login(user);
            Patient patient = patientService.findByUser(user1).get();
            List<Object> body = new ArrayList<>();
            body.add(user1.getToken());
            body.add(user1.getRol());
            body.add(patient.getDni());
            response = ResponseEntity.ok().body(body);
            System.out.println(user);
        } else {
            response=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            log.error("Usuario no encontrado");
        }
        return response;
    }


}

package com.proyectointegrador.controller;

import com.proyectointegrador.entity.Odontologist;
import com.proyectointegrador.service.OdontologistService;
import com.proyectointegrador.service.UserService;
import org.apache.coyote.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologists")
public class OdontologistController {
    Logger log = Logger.getLogger(OdontologistController.class);
    @Autowired
    private OdontologistService odontologistService;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/register")
    public ResponseEntity<Odontologist> register(Odontologist odontologist, @RequestHeader("Authorization") String token) {
        ResponseEntity<Odontologist> response =null;
        if(userService.isAdminUser(token)) {
            if (odontologist != null) {
                response = ResponseEntity.ok(odontologistService.register(odontologist));
                log.info(odontologist.toString());
            } else {
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                log.error("Ocurrió un error al añadir un odontólogo");
            }
        } else {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            log.error("No tiene autorización para realizar esta acción");
        }
            return  response;
    }


    @GetMapping("GetInfoByInt/{licenseNumber}")
    public ResponseEntity<?> findbyLicenseNumber(@PathVariable("licenseNumber") Integer licenseNumber, @RequestHeader("Authorization") String token) {
        ResponseEntity<Optional<Odontologist>> response = null;
        if(userService.isAdminUser(token)) {
            if (odontologistService.findbyLicenseNumber(licenseNumber).isPresent()) {
                response = ResponseEntity.ok(odontologistService.findbyLicenseNumber(licenseNumber));
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            log.error("No tiene autorización para realizar esta acción");
        }
        return response;
    }

    @GetMapping("GetInfoByString/{lastName}")
    public ResponseEntity<?> findByLastName(@PathVariable("lastName") String lastName,  @RequestHeader("Authorization") String token) {
        ResponseEntity<Optional<Odontologist>> response = null;
        if(userService.isAdminUser(token)) {
            if (odontologistService.findbyLastName(lastName).isPresent()) {
                response = ResponseEntity.ok(odontologistService.findbyLastName(lastName));
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            log.error("No tiene autorización para realizar esta acción");
        }
        return response;
    }

    @GetMapping("/")
    public ResponseEntity<?> listAll(){
        ResponseEntity<List<Odontologist>> response = null;
        if(odontologistService.list() != null){
            response = ResponseEntity.ok(odontologistService.list());
        }else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;

    }

    @PutMapping("/modify")
    public ResponseEntity<Odontologist> modify(Odontologist odontologist, @RequestHeader("Authorization") String token){
        ResponseEntity<Odontologist> response = null;
        if(userService.isAdminUser(token)) {
            Odontologist odontologist1 = odontologistService.findbyLicenseNumber(odontologist.getLicenseNumber()).get();
            odontologist1.setUpdateData(odontologist);
            if (odontologist1 != null) {
                response = ResponseEntity.ok(odontologistService.update(odontologist1));

            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            log.error("No tiene autorización para realizar esta acción");
        }
        return response;
    }

    @DeleteMapping("/delete/{licenseNumber}")
    public ResponseEntity<String> delete(@PathVariable("licenseNumber") Integer licenseNumber, @RequestHeader("Authorization") String token){
        ResponseEntity<String> response = null;
        if(userService.isAdminUser(token)) {
            if (odontologistService.findbyLicenseNumber(licenseNumber) != null) {
                System.out.println(licenseNumber);
                odontologistService.delete(licenseNumber);
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
package com.proyectointegrador.controller;

import com.proyectointegrador.entity.Odontologist;
import com.proyectointegrador.service.OdontologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/odontologists")
public class OdontologistController {
    @Autowired
    private OdontologistService odontologistService;

    @PostMapping(path = "/register", consumes = "application/json")
    public ResponseEntity<Odontologist> register(@RequestBody Odontologist odontologist) {
        return ResponseEntity.ok(odontologistService.register(odontologist));
    }

    @GetMapping("/search")
    public ResponseEntity<Optional<Odontologist>> find(@Param("licenseNumber") Integer licenseNumber) {
        ResponseEntity<Optional<Odontologist>> response = null;
        if (odontologistService.find(licenseNumber).isPresent()){
            response=ResponseEntity.ok(odontologistService.find(licenseNumber));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @PutMapping("/modify")
    public ResponseEntity<Odontologist> modify(@RequestBody Odontologist odontologist){
        ResponseEntity<Odontologist> response = null;
        Odontologist odontologist1 = odontologistService.find(odontologist.getLicenseNumber()).get();
        odontologist1.setUpdateData(odontologist);
        if(odontologist1 != null) {
            response=ResponseEntity.ok(odontologistService.update(odontologist1));

        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(@Param("licenseNumber") Integer licenseNumber){
        ResponseEntity<String> response = null;
        if (odontologistService.find(licenseNumber) != null) {
            System.out.println(licenseNumber);
            odontologistService.delete(licenseNumber);
            response = ResponseEntity.ok("Deletec");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

}
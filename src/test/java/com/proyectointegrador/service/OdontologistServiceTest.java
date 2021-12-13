package com.proyectointegrador.service;

import com.proyectointegrador.ProyectoIntegradorApplication;
import com.proyectointegrador.entity.Odontologist;
import com.proyectointegrador.entity.Patient;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;


@SpringBootTest
class OdontologistServiceTest {

    Logger log = Logger.getLogger(OdontologistServiceTest.class);

    @Autowired
    OdontologistService odontologistService;
    Odontologist odontologist;
    Odontologist odontologist1;

    @BeforeEach
    void setUp() {
        odontologist = new Odontologist("Lari", "Schneeberger", 43000000);
        odontologist1 = new Odontologist("Santi", "Lamberti", 44000000);

    }

    @AfterEach
    void cleanUp(){
        odontologistService.delete(odontologist.getLicenseNumber());
        odontologistService.delete(odontologist1.getLicenseNumber());
    }


    @Test
    void register() {
        odontologistService.register(odontologist);
        odontologistService.register(odontologist1);
        log.info(odontologistService.findbyLicenseNumber(odontologist.getLicenseNumber()));
        log.info(odontologistService.findbyLicenseNumber(odontologist1.getLicenseNumber()));
    }

    @Test
    void find() {
        odontologistService.register(odontologist);
        log.info(odontologistService.findbyLicenseNumber(odontologist.getLicenseNumber()));
    }

    @Test
    void update() {
        Odontologist odontologist2 = new Odontologist("Larita", "Schneeberger", 43000000);
        odontologistService.register(odontologist);
        Odontologist odontologist3 = odontologistService.findbyLicenseNumber(odontologist.getLicenseNumber()).get();
        odontologist = odontologistService.findbyLicenseNumber(odontologist.getLicenseNumber()).get();

        odontologist.setUpdateData(odontologist2);
        odontologist3.setUpdateData(odontologist2);
        odontologistService.update(odontologist3);
        assertEquals(odontologist3.toString(), odontologist.toString());
        log.info((odontologistService.findbyLicenseNumber(43000000)));
    }

    @Test
    void delete() {
        odontologistService.register(odontologist1);
        odontologistService.delete(44000000);
        log.info(odontologistService.findbyLicenseNumber(44000000));
    }

    @Test
    void list() {
        odontologistService.register(odontologist);
        odontologistService.register(odontologist1);
        log.info(odontologistService.list());
    }
}
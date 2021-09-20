package com.proyectointegrador.service;

import com.proyectointegrador.ProyectoIntegradorApplication;
import com.proyectointegrador.entity.Patient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.apache.log4j.Logger;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = {ProyectoIntegradorApplication.class})
class PatientServiceTest {

    Logger log = Logger.getLogger(PatientServiceTest.class);

    @Autowired
    PatientService pacienteService;
    Patient patient;
    Patient patient1;

    @BeforeEach
    void setUp() {
        patient = new Patient("Lari", "Schneeberger", 43000000, "tandil");
        patient1 = new Patient("Santi", "Lamberti", 44000000, "palermo");
    }

    @AfterEach
    void cleanUp(){
        pacienteService.delete(patient.getDni());
        pacienteService.delete(patient1.getDni());
    }


    @Test
    void register() {
        pacienteService.register(patient);
        pacienteService.register(patient1);
        log.info(pacienteService.find(patient.getDni()));
        log.info(pacienteService.find(patient1.getDni()));
    }

    @Test
    void find() {
        pacienteService.register(patient);
        log.info(pacienteService.find(patient.getDni()));
    }

    @Test
    void update() {
        Patient patient2 = new Patient("Larita", "Schneeberger", 43000000, "tandil");
        pacienteService.register(patient);
        Patient patient3 = pacienteService.find(patient.getDni()).get();
        patient = pacienteService.find(patient.getDni()).get();

        patient.setUpdateData(patient2);
        patient3.setUpdateData(patient2);
        pacienteService.update(patient3);
        assertEquals(patient3.toString(), patient.toString());
        log.info((pacienteService.find(43000000)));
    }

    @Test
    void delete() {
        pacienteService.register(patient1);
        pacienteService.delete(44000000);
        log.info(pacienteService.find(44000000));
    }

    @Test
    void list() {
        pacienteService.register(patient);
        pacienteService.register(patient1);
        log.info(pacienteService.list());
    }
}
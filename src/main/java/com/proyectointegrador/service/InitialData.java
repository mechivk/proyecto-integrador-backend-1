package com.proyectointegrador.service;

import com.proyectointegrador.entity.*;
import com.proyectointegrador.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InitialData implements ApplicationRunner {
    @Autowired
    private PatientService patientService;

    @Autowired
    private OdontologistService odontologistService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        User user = new User("admin", "adminpass", "m@gmail.com", Rol.ADMIN);
        User user1 = new User("patientpass", "patientpass", "u@gmail.com", Rol.USER);

        Patient patient = new Patient("Mechi", "van Keulen", 40134526, "flores");
        Patient patient1 = new Patient("Lari", "Schneeberger", 4400000, "tandil");
        patient.setUser(user);
        patient1.setUser(user1);
        Odontologist odontologist = new Odontologist("Laura", "Tanga", 454888);
        Odontologist odontologist1 = new Odontologist("Mila", "Nesa", 499685);
        patientService.register(patient);
        patientService.register(patient1);
        odontologistService.register(odontologist);
        odontologistService.register(odontologist1);

        Patient addedPatient = patientService.find(40134526).get();
        Patient addedPatient2 = patientService.find(4400000).get();
        Odontologist addedOdontologist = odontologistService.findbyLicenseNumber(454888).get();
        Odontologist addedOdontologist2 = odontologistService.findbyLicenseNumber(499685).get();
        Appointment appointment = new Appointment(addedPatient, addedOdontologist, LocalDateTime.of(2022,7,12,10,30), "normal checkup");
        Appointment appointment1 = new Appointment(addedPatient2, addedOdontologist2, LocalDateTime.of(2021,9,29,11,00), "urgent checkup");

        appointmentService.add(appointment);
        appointmentService.add(appointment1);





    }
}
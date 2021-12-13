package com.proyectointegrador.service;

import com.proyectointegrador.entity.Appointment;
import com.proyectointegrador.entity.Odontologist;
import com.proyectointegrador.entity.Patient;
import com.proyectointegrador.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService{

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment add(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Optional<List<Appointment>> findByPatient(Patient patient) {
        return appointmentRepository.findByPatient(patient);
    }

    public Optional<List<Appointment>> findByOdontologist(Odontologist odontologist) {
        return appointmentRepository.findByOdontologist(odontologist);
    }

    public Optional<Appointment> find(LocalDateTime date, Odontologist odontologist){
        return appointmentRepository.findByDateAndOdontologist(date, odontologist);
    }

    public Optional<Appointment> findById(Integer id){
        return appointmentRepository.findById(id);
    }


    public Appointment update(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public void delete(Appointment appointment) {
        appointmentRepository.deleteById(appointment.getId());
    }

    public List<Appointment> list() {
        System.out.println(appointmentRepository.findAll());
        return appointmentRepository.findAll();

    }
}

package com.proyectointegrador.service;

import com.proyectointegrador.entity.Patient;
import com.proyectointegrador.entity.User;
import com.proyectointegrador.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService{

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository pacienteRepository) {
        this.patientRepository = pacienteRepository;
    }

    public Patient register(Patient patient) {
        return patientRepository.save(patient);
    }

    public Optional<Patient> find(Integer dni) {
        return patientRepository.findByDni(dni);
    }

    public Optional<Patient> findByUser(User user) {
        return patientRepository.findByUser(user);
    };

    public Patient update(Patient patient) {
        return patientRepository.save(patient);
    }

    public void delete(Integer dni) {
        patientRepository.deleteByDni(dni);
    }

    public List<Patient> list() {
        return patientRepository.findAll();
    }
}

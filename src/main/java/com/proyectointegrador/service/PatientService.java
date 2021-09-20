package com.proyectointegrador.service;

import com.proyectointegrador.entity.Patient;
import com.proyectointegrador.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IService<Patient>{

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository pacienteRepository) {
        this.patientRepository = pacienteRepository;
    }

    @Override
    public Patient register(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Optional<Patient> find(Integer dni) {
        return patientRepository.findByDni(dni);
    }

    @Override
    public Patient update(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void delete(Integer dni) {
        patientRepository.deleteByDni(dni);
    }

    @Override
    public List<Patient> list() {
        return patientRepository.findAll();
    }
}

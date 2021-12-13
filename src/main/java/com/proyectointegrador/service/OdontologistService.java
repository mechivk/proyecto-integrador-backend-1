package com.proyectointegrador.service;

import com.proyectointegrador.entity.Odontologist;
import com.proyectointegrador.repository.OdontologistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologistService {

    private final OdontologistRepository odontologistRepository;

    @Autowired
    public OdontologistService(OdontologistRepository odontologoRepository) {
        this.odontologistRepository = odontologoRepository;
    }

    public Odontologist register(Odontologist odontologo) {
        return odontologistRepository.save(odontologo);
    }

    public Optional<Odontologist> findbyLicenseNumber(Integer licenseNumber) {
        return odontologistRepository.findByLicenseNumber(licenseNumber);
    }

    public Optional<Odontologist> findbyLastName(String lastName) {
        return odontologistRepository.findByLastName(lastName);
    }

    public Odontologist update(Odontologist odontologo) {
        return odontologistRepository.save(odontologo);
    }

    public void delete(Integer licenseNumber) {
        odontologistRepository.deleteByLicenseNumber(licenseNumber);
    }

    public List<Odontologist> list() {
        return odontologistRepository.findAll();
    }
}

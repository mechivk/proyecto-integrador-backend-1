package com.proyectointegrador.service;

import com.proyectointegrador.entity.Odontologist;
import com.proyectointegrador.repository.OdontologistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologistService implements IService<Odontologist> {

    private final OdontologistRepository odontologistRepository;

    @Autowired
    public OdontologistService(OdontologistRepository odontologoRepository) {
        this.odontologistRepository = odontologoRepository;
    }

    @Override
    public Odontologist register(Odontologist odontologo) {
        return odontologistRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologist> find(Integer licenseNumber) {
        return odontologistRepository.findByLicenseNumber(licenseNumber);
    }

    @Override
    public Odontologist update(Odontologist odontologo) {
        return odontologistRepository.save(odontologo);
    }

    @Override
    public void delete(Integer licenseNumber) {
        odontologistRepository.deleteByLicenseNumber(licenseNumber);
    }

    @Override
    public List<Odontologist> list() {
        return odontologistRepository.findAll();
    }
}

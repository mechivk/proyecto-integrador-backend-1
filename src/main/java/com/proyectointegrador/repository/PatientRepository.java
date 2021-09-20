package com.proyectointegrador.repository;

import com.proyectointegrador.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByDni(Integer dni);

    @Transactional
    void deleteByDni(Integer dni);
}

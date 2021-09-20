package com.proyectointegrador.repository;

import com.proyectointegrador.entity.Odontologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface OdontologistRepository extends JpaRepository<Odontologist, Long> {
    Optional<Odontologist> findByLicenseNumber(Integer licenseNumber);

    @Transactional
    void deleteByLicenseNumber(Integer licenseNumber);
}

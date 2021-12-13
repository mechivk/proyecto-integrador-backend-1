package com.proyectointegrador.repository;

import com.proyectointegrador.entity.Appointment;
import com.proyectointegrador.entity.Odontologist;
import com.proyectointegrador.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    Optional<List<Appointment>> findByPatient(Patient patient);
    Optional<List<Appointment>> findByOdontologist(Odontologist odontologist);
    Optional<Appointment> findByDateAndOdontologist(LocalDateTime date, Odontologist odontologist);
    Optional<Appointment> findById(Integer id);

    @Transactional
    void deleteById(Integer id);


}

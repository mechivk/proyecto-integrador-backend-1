package com.proyectointegrador.service;

import com.proyectointegrador.entity.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService implements IService<Appointment>{

    @Override
    public Appointment register(Appointment appointment) {
        return null;
    }

    @Override
    public Optional<Appointment> find(Integer id) {
        return Optional.empty();
    }

    @Override
    public Appointment update(Appointment appointment) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Appointment> list() {
        return null;
    }
}

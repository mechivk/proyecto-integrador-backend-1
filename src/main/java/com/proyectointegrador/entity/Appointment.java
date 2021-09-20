package com.proyectointegrador.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Appointment {
    @Id
    @SequenceGenerator(name = "domicilio_sequence", sequenceName = "domicilio_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "domicilio_sequence")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "paciente_id")
    private Patient paciente;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "odontologo_id")
    private Odontologist odontologo;
    private Date date;

    public Appointment(Patient paciente, Odontologist odontologo, Date date) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public Patient getPaciente() {
        return paciente;
    }

    public void setPaciente(Patient paciente) {
        this.paciente = paciente;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", date=" + date +
                '}';
    }

}

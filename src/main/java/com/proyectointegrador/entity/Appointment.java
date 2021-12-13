package com.proyectointegrador.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Appointment {
    @Id
    @SequenceGenerator(name = "appointment_sequence", sequenceName = "appointment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_sequence")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonManagedReference
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odontologist_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonManagedReference
    private Odontologist odontologist;

    @Column
    private String description;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    public Appointment(Patient patient, Odontologist odontologist, LocalDateTime date, String description) {
        this.patient = patient;
        this.odontologist = odontologist;
        this.date = date;
        this.description = description;
    }


    public Appointment(){}

    public Integer getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Odontologist getOdontologist() {
        return odontologist;
    }

    public void setOdontologist(Odontologist odontologist) {
        this.odontologist = odontologist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patient=" + patient +
                ", odontologist=" + odontologist +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}

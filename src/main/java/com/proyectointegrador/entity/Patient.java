package com.proyectointegrador.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Patient {
    @Id
    @SequenceGenerator(name = "paciente_sequence", sequenceName = "paciente_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_sequence")
    private Integer id;

    @NotNull
    @Column(unique = true)
    private Integer dni;

    @Column(length = 30)
    private String name;

    @Column(length=30)
    private String lastName;

    @Column
    private LocalDate dateOfRegistration = LocalDate.now();

    @Column(length = 100)
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    @JsonBackReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Appointment> appointments;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "user")
    private User user;

    public Patient(){};


    public Patient(String name, String lastName, Integer dni, String address) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.dateOfRegistration = LocalDate.now();
        this.address = address;
    }

    public void setUpdateData(Patient patient){
        this.name = patient.getName();
        this.lastName =patient.getLastName();
        this.address = patient.getAddress();
    }


    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", dni=" + dni +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfRegistration=" + dateOfRegistration +
                ", address='" + address + '\'' +
                '}';
    }
}

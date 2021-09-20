package com.proyectointegrador.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate dateOfRegistration;

    @Column(length = 100)
    private String address;

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

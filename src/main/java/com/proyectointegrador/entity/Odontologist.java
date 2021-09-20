package com.proyectointegrador.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class Odontologist {
    @Id
    @SequenceGenerator(name = "odontologo_sequence", sequenceName = "odontologo_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odontologo_sequence")
    private Integer id;
    private String name;
    private String lastName;
    private Integer licenseNumber;

    public Odontologist(String name, String lastName, Integer licenseNumber) {
        this.name = name;
        this.lastName = lastName;
        this.licenseNumber = licenseNumber;
    }

    public Odontologist(){};

    public void setUpdateData(Odontologist odontologist){
        this.name = odontologist.getName();
        this.lastName =odontologist.getLastName();
    }


    @Override
    public String toString() {
        return "Odontologist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", licenseNumber=" + licenseNumber +
                '}';
    }
}

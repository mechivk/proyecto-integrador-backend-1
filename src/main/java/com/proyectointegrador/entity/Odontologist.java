package com.proyectointegrador.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
    @NotNull
    @Column(unique = true, nullable = false)
    private Integer licenseNumber;

    @OneToMany(mappedBy = "odontologist", cascade = CascadeType.ALL)
    @JsonBackReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Appointment> appointments;

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

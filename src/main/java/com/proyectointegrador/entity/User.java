package com.proyectointegrador.entity;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Integer id;

    @Column(length=50, unique = true)
    private String username;

    @Column(length=50)
    private String password;

    @Column(unique = true)
    private String email;

    private String token;

    private LocalDateTime expirationTime;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public User(String username, String password, String email, Rol rol) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.rol = rol;
    }

    public User(){};


    public Integer getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", expirationTime=" + expirationTime +
                ", rol=" + rol +
                '}';
    }
}

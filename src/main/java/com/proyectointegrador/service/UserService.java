package com.proyectointegrador.service;

import com.proyectointegrador.entity.Rol;
import com.proyectointegrador.entity.User;
import com.proyectointegrador.jwt.Jwt;
import com.proyectointegrador.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private Jwt jwt;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, Jwt jwt) {
        this.userRepository = userRepository;
        this.jwt = jwt;
    }

    public User login(User user){
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if(userOptional.isPresent()){
            User user1 = userOptional.get();
            if(user1.getPassword().equals(user.getPassword())){
                if ((user1.getToken() == null) || (LocalDateTime.now().isAfter(user1.getExpirationTime()))) {
                    LocalDateTime dateTime = LocalDateTime.now().plusHours(1);
                    user1.setToken(jwt.getToken(user1, dateTime));
                    user1.setExpirationTime(dateTime);
                }
                user = userRepository.save(user1);
                System.out.println(user);
            }
        }
        return user;
    }

    public Integer getUserId(String token) {
        return jwt.decodeToken(token);
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Boolean isValidToken(String token){
        try {
            jwt.decodeToken(token);
            return true;
        } catch (Exception err) {

            return false;
        }
    }

    public Boolean isAdminUser(String token){

        if(isValidToken(token)){
            User user = findById(getUserId(token)).get();
            return user.getRol() == Rol.ADMIN;
        } else {
            return false;
        }
}}

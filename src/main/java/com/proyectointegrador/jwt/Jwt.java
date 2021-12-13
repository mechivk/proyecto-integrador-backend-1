package com.proyectointegrador.jwt;
import com.proyectointegrador.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;

@Component
public class Jwt {
    private static Key key;

    public String getToken(User user, LocalDateTime dateTime) {

        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String jws = Jwts.builder().setSubject(json(user, dateTime)).signWith(key).compact();
        return jws;
    }

    public Integer decodeToken(String jwsString) {
        Jws<Claims> jws;

        jws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwsString);

        return Integer.parseInt(jws.getBody().toString().split("'")[1]);
    }

    public String json(User user, LocalDateTime dateTime){
        return "{" +
                "id='" + user.getId() +
                "', userName='" + user.getUsername() + '\'' +
                "expiratinTime='" + dateTime.now().plusHours(1) +'\'' +
                '}';
    }
}
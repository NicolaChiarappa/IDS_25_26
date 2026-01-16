package it.unicam.coloni.hackhub.context.identity.infrastructure.security;

import io.jsonwebtoken.Jwts;
import it.unicam.coloni.hackhub.context.identity.application.utilities.JWTHelper;
import it.unicam.coloni.hackhub.context.identity.domain.models.User;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;



public class SpringJWTHelper implements JWTHelper {


    SecretKey key = Jwts.SIG.HS256.key().build();


    @Override
    public String generate(User user, Integer duration) {
        Long seconds = Instant.now().getEpochSecond()+86400*duration;
        Date expDate =  Date.from(Instant.ofEpochSecond(seconds));
        System.out.println(expDate);
        return Jwts.builder()
                .subject(user.getUsername())
                .expiration(expDate)
                .signWith(key)
                .compact();
    }


    @Override
    public String extractUsername(String token){

            return Jwts.parser().verifyWith(key).build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();

    }



}

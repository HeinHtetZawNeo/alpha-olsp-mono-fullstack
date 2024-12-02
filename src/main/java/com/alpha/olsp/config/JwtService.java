package com.alpha.olsp.config;

import com.alpha.olsp.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${jwt.secretkey}")
    public String SECRET;

    public String generateToken(User user) {
        String token = Jwts
                .builder()
                .issuedAt(new Date())
                .issuer("com.alpha.olsp")
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .subject(user.getUsername())//identity
                //.claim("authorities", populateAuthorities(userDetails))
                .claim("role", "ROLE_"+user.getClass().getSimpleName().toUpperCase())
                .signWith(signInKey())
                .compact();
        return token;
    }

    private SecretKey signInKey() {
        //Hash based message authentication code
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }

    public String populateAuthorities(UserDetails userDetails) {
        return userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
//                .map(authority -> authority.getAuthority())
                .collect(Collectors.joining(","));
    }

    public Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith(signInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}

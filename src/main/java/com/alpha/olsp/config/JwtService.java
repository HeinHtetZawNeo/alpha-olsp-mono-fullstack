package com.alpha.olsp.config;

import com.alpha.olsp.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    private final Set<String> tokenBlacklist = new HashSet<>();
    //@Value("${env.jwt.secret}")
    public String SECRET="b4af4ed17eb520b35daf4943ff068b647c148e8bbc32b7527013c5ae47d2066a";

    public String generateToken(User user) {
        logger.info("Generating token for user: {} " + user);
        String token = Jwts
                .builder()
                .issuedAt(new Date())
                .issuer("com.alpha.olsp")
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .subject(user.getUsername())//identity
//                .claim("authorities", populateAuthorities(user))
                .claim("role", "ROLE_" + user.getClass().getSimpleName().toUpperCase())
                .signWith(signInKey())
                .compact();
        return token;
    }

    private SecretKey signInKey() {
        //Hash based message authentication code
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(signInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}

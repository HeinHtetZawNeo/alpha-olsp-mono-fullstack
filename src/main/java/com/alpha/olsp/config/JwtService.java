package com.alpha.olsp.config;

import com.alpha.olsp.model.User;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService {
    private final Set<String> tokenBlacklist = new HashSet<>();
    @Value("${jwt.secret}")
    public String SECRET;

    public String generateToken(User user) {
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

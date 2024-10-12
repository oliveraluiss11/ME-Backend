package com.jayway.market_express.common.util;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.jayway.market_express.common.constant.MessageConstant.EMPTY;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final Environment environment;

    public String generateToken(JwtPayload jwtPayload) {
        Map<String, Object> claims = new HashMap<>();
        Gson gson = new Gson();
        String subject = gson.toJson(jwtPayload);
        return createToken(claims, subject);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        String secretKey = Optional.ofNullable(environment.getProperty("jwt.key")).orElse(EMPTY);
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Expiración de 10 horas
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    public JwtPayload extractPayload(String token) {
        String payload = extractClaim(token, Claims::getSubject);
        Gson gson = new Gson();
        return gson.fromJson(payload, JwtPayload.class);
    }


    public <T> T extractClaim(String token, java.util.function.Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        String secretKey = Optional.ofNullable(environment.getProperty("jwt.key")).orElse(EMPTY);
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

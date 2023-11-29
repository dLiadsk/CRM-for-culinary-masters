package com.example.crm.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

public class Token {
    @Getter
    private final String token;

    private Token(String token){
        this.token = token;
    }

    public static Token of(Long userId, Long validityInMinutes, String secretKey) {
        var issueDate = Instant.now();
        return new Token(
                Jwts.builder()
                    .claim("user_id",userId)
                    .setIssuedAt(Date.from(issueDate))
                    .setExpiration(Date.from(issueDate.plus(validityInMinutes, ChronoUnit.MINUTES)))
                    .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8)))
                    .compact());
    }

    public static Token of(String token){
        return new Token(token);
    }

    public static Long from(String token, String secretKey) {
        Claims claims = (Claims) Jwts.parserBuilder().setAllowedClockSkewSeconds(5)
                .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parse(token)
                .getBody();

// Перевірте, чи claims не є null перед викликом методу get
        if (claims != null) {
            Long userId = claims.get("user_id", Long.class);
            return userId;
        } else {
            // Обробка випадку, коли claims є null
            return null; // або повернення значення за замовчуванням
        }
    }

}

package com.jobportal.job_portal_backend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {

    private final Key key;
    private final long expirationTime;

    public JWTUtil(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") String expirationTime){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationTime = Long.parseLong(expirationTime);
    }

    // Generate token
    public String generateToken(String username){
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    public Claims extractClaims(String token){
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Extract username from token
    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }

    public boolean validateToken(String username, UserDetails userDetails, String token) {
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }
}

package com.example.journalapp.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {
    private static final String SECRET = "bXlTdXBlclNlY3JldEtleUZvckpXVDEyMzQ1Njc4OTA=";

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*30))
                .addClaims(new HashMap<>())
                .signWith(getSignedKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public Key getSignedKey()
    {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public Claims verifySignatureAndExtractClaims(String token){
          return Jwts.parserBuilder()
                  .setSigningKey(getSignedKey())
                  .build()
                  .parseClaimsJws(token)
                  .getBody();
    }

    public String extractUserName (String token){
        return verifySignatureAndExtractClaims(token).getSubject();
    }

    public Date getExpirationDate(String token){
        return verifySignatureAndExtractClaims(token).getExpiration();
    }
    public Boolean isTokenExpired(String token){
        return getExpirationDate(token).before(new Date());
    }
}

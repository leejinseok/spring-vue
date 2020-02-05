package com.example.vue.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private Key key;

    public static long PLUS_MILLS = (1000 * 60 * 60 * 24) * 30L;

    public JwtUtil(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createToken(Long userId, String name, String role) {

        JwtBuilder builder = Jwts.builder()
            .claim("userId", userId)
            .claim("name", name)
            .claim("role", role);

        return builder
            .signWith(key, SignatureAlgorithm.HS256)
            .setExpiration(expireTime())
            .compact();

    }

    private Date expireTime() {
        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + PLUS_MILLS);
        return expireTime;
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
            .setSigningKey(key)
            .parseClaimsJws(token)
            .getBody();

    }

    public boolean isUsable(String token) {
        try {
            Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

            return true;

        } catch (Exception e) {
            throw new RuntimeException("권한이 유효하지 않습니다.");
        }
    }
}

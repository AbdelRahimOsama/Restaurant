package com.spring.resturant.config;

import com.spring.resturant.dto.CustomerDto;
import com.spring.resturant.helper.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;

@Component
public class TokenHandler {

    private final String secret;
    private final Duration time;
    private final JwtParser jwtParser;

    public TokenHandler(JwtToken jwtToken) {
        this.secret = jwtToken.getSecret();
        this.time = jwtToken.getTime();

        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
    }

    // 🔐 generate token
    public String generateToken(CustomerDto customerDto) {

        Date issuedAt = new Date();
        Date expiration = Date.from(issuedAt.toInstant().plus(time));

        return Jwts.builder()
                .setSubject(customerDto.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .claim("roles", customerDto.getRoleDtos())
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    // 🔍 extract username فقط
    public String extractUsername(String token) {
        return jwtParser.parseClaimsJws(token).getBody().getSubject();
    }

    // ✅ validate token فقط
    public boolean isValid(String token) {
        try {
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
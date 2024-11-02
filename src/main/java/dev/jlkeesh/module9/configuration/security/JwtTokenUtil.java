package dev.jlkeesh.module9.configuration.security;

import dev.jlkeesh.module9.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenUtil {

    private final Key signingKey;
    private final JwtProperties jwtProperties;
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    public JwtTokenUtil(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecretKey()));
    }

    public String generateAccessToken(String username, Map<String, Object> claims) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setIssuer(jwtProperties.getIssuer())
                .setExpiration(jwtProperties.getAccessTokenTtl())
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .addClaims(claims)
                .compact();
    }

    public String generateRefreshToken(String username, Map<String, Object> claims) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setIssuer(jwtProperties.getIssuer())
                .setExpiration(jwtProperties.getRefreshTokenTtl())
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .addClaims(claims)
                .compact();
    }

    public boolean isValid(String token) {
        try {
            Claims claims = getClaims(token);
            Date expiration = claims.getExpiration();
            return expiration.after(new Date());
        } catch (Exception e) {
            logger.error("token is not valid", e);
        }
        return false;
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}

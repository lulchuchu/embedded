package com.example.demo.Security.jwt;

import com.example.demo.Security.UserDetail;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    private static final String SECRET_KEY = "ba4e180d007b806560bfbe63af355435f551e2db718ee22ff1aaf4c9d4c7ef884bd9868858818a8ed547d5e0975dc291ecd5c0dce7d8e2a9bdd27e14f7c96d6d";
    private static final long EXPIRE_TIME = 604800000L;

    public String generateToken(Authentication authentication) {
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();

        return Jwts.builder().setSubject((userDetail.getUsername())).setIssuedAt(new Date()).setExpiration(new Date((new Date()).getTime() + EXPIRE_TIME * 1000)).signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    public String getUserNameFromJwtToken(String token) {

        String userName = Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getSubject();
        return userName;
    }

    public Integer getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();

        return Integer.parseInt(claims.getSubject());
    }
}

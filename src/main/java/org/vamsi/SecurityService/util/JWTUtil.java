package org.vamsi.SecurityService.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

    private static final String SECRET = "196f05070da54e029aed7318875d15c9dhatgskdedjhsytydhheekkshciowahffsbmluqlalaqyyuckamhhebnsqppafemaizgeh";

    public String generateToken(String userName) {

        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    private String createToken(Map<String, Object> claims, String userName) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {

        byte[] secret = Decoders.BASE64.decode(SECRET);

        return Keys.hmacShaKeyFor(secret);
    }
    public boolean validate(String token) {
        return false;
    }
}

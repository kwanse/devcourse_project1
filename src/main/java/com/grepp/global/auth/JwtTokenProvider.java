package com.grepp.global.auth;

import com.grepp.domain.admin.Admin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

import static com.grepp.global.Const.ISSUER;

@Component
public class JwtTokenProvider {

    private static final String KEY = UUID.randomUUID().toString();

    public static String createAccessToken(Admin admin, long expireTime) {
        return Jwts.builder()
                .signWith(getTokenKey())
                .subject(admin.getLoginId())
                .issuer(ISSUER)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expireTime))
                .compact();
    }

    public String validateAccessToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(getTokenKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.getSubject();
        } catch (Exception e) {
            throw new SecurityException("Invalid token");
        }
    }

    private static SecretKey getTokenKey() {
        byte[] keyBytes = Decoders.BASE64.decode(KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}



package com.snake.common.securities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake.common.utils.KeyUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Getter
@Setter
@Component
@RequiredArgsConstructor
public class AppJwtTokenProvider {

    private final ObjectMapper objectMapper;

    @Value("${security.token-time-out-ms:86400000}")
    private Long tokenTimeOutMs;

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser()
                    .verifyWith(genPublicKey())
                    .build()
                    .parseSignedClaims(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        } catch (Exception ex) {
            return false;
        }
        return false;
    }

    public boolean checkTokenExp(String authToken) {
        try {
            Jwts.parser()
                    .verifyWith(genPublicKey())
                    .build()
                    .parseSignedClaims(authToken);
            return false;
        } catch (ExpiredJwtException ex) {
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public String resolveToken(HttpServletRequest req) {
        String authorizationHeader = req.getHeader("Authorization");
        if (StringUtils.isBlank(authorizationHeader)) {
            return null;
        }
        String bearerToken = authorizationHeader.trim();
        if (bearerToken.startsWith("Bearer") && bearerToken.length() > 7) {
            return bearerToken.substring(7).trim();
        }
        return null;
    }

    public AppUserDetails getPrincipal(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(genPublicKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(claims), AppUserDetails.class);
        } catch (IOException e) {
            return new AppUserDetails();
        }
    }

    private PublicKey genPublicKey() {
        try {
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
                    Base64.getDecoder().decode(KeyUtils.getKey("keys/public_key.crt", false))
            );
            final String rsa = "RSA";
            return KeyFactory.getInstance(rsa).generatePublic(publicKeySpec);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    private PrivateKey genPrivateKey() {
        try {
            val spec = new PKCS8EncodedKeySpec(
                    Base64.getDecoder().decode(KeyUtils.getKey("keys/private_key.crt", false))
            );
            val factory = KeyFactory.getInstance("RSA");
            return factory.generatePrivate(spec);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public String genToken(AppUserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getSub())
                .claim(AppUserDetails.Fields.username, userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + tokenTimeOutMs)) // ms
                .claim(AppUserDetails.Fields.roles, userDetails.getRoles())
                .claim(AppUserDetails.Fields.permissions, userDetails.getPermissions())
                .signWith(genPrivateKey())
                .compact();
    }
}

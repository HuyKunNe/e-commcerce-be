package com.huykun.ecommercebe.utils;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

import com.huykun.ecommercebe.config.JwtConfig;
import com.huykun.ecommercebe.entity.Account;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Date;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class Utils {
    public static String buildJWT(Authentication authenticate, Account accountAuthenticated, SecretKey secretKey,
            JwtConfig jwtConfig) {
        String token = Jwts.builder().setSubject(authenticate.getName())
                .claim("authorities", authenticate.getAuthorities())
                .claim("email", accountAuthenticated.getEmail())
                .claim("accountId", accountAuthenticated.getId())
                .setIssuedAt((new Date()))
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey).compact();
        return token;
    };

}
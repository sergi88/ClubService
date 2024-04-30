package com.scamps.ClubService.security;

import com.scamps.ClubService.ValuesConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.KeyAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.Password;

import java.util.Date;

public class JwtConfig {
    private static final String PWD_STRING = "spring.security.passwordString";
    private static final KeyAlgorithm<Password, Password> KEY_ALGORITHM = Jwts.KEY.PBES2_HS512_A256KW;
    private static final AeadAlgorithm ENCRYPTION_ALGORITHM = Jwts.ENC.A256GCM;
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 2;
    private static final String AUTH_HEADER_NAME = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String CONTENT_TYPE = "application/json";


    public static Password getPassword() {
        return Keys.password(ValuesConfig.getMessage(PWD_STRING).toCharArray());
    }

    public static KeyAlgorithm<Password, Password> getKeyAlgorithm() {
        return KEY_ALGORITHM;
    }

    public static AeadAlgorithm getEncryptionAlgorithm() {
        return ENCRYPTION_ALGORITHM;
    }

    public static Date getExpirationTime() {
        return new Date(System.currentTimeMillis() + EXPIRATION_TIME);
    }

    public static String getAuthHeaderName() {
        return AUTH_HEADER_NAME;
    }

    public static String getTokenPrefix() {
        return TOKEN_PREFIX;
    }

    public static String getContentType() {
        return CONTENT_TYPE;
    }

}

package com.fetch.common.utils;

import com.fetch.common.core.domain.LoginUser;
import com.fetch.common.exception.IllegalParameterException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

/**
 * @author jiang chen
 * @version 1.0
 * @description: TODO
 * @date 2024/3/7 9:03
 */
public class JwtUtils {

    private final static String SECRET = "jjYq13nkjshiuT24g7ehT1fdiq38";

    private final static Long EXPIRATION = 60 * 60 * 1000L;

//    public static String getUUID() {
//        return UUID.randomUUID().toString().replaceAll("-", "");
//    }

    public static String createJWT(String username, String userId, Long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generateKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = EXPIRATION;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expirationDate = new Date(expMillis);
        return Jwts.builder()
                .setId(userId)
                .setSubject(username)
                .setIssuer("admin")
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, secretKey)
                .setExpiration(expirationDate)
                .compact();
    }

    public static SecretKey generateKey() {
//        String decode = new String(Base64.getDecoder().decode(JwtUtil.SECRET.replace("\r\n","")),"utf-8");
        byte[] decodeKey = Base64.getEncoder().encode(SECRET.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(decodeKey, 0, decodeKey.length, "AES");
    }

    public static Claims parseJWT(String jwt) {
        SecretKey secretKey = generateKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static String refreshToken(String token, long ttlMillis) {
        if (token == null || token.isEmpty()) {
            throw new IllegalParameterException("token不能为空");
        }
        Claims claims = JwtUtils.parseJWT(token);
        if (claims.getExpiration().before(new Date())) {
            return JwtUtils.createJWT(claims.getSubject(), claims.getId(), ttlMillis);
        }
        return token;
    }

    public String verifyJwt(LoginUser loginUser, String token) {

        if (Objects.isNull(loginUser) || token == null || token.isEmpty()) {
            return null;
        }
        Claims claims = parseJWT(token);
        Long expirationTime = claims.getExpiration().getTime();
        Long currentTime = System.currentTimeMillis();
        if (expirationTime - currentTime <= 1000 * 60 * 5) {
            return createJWT(claims.getSubject(), claims.getId(), expirationTime);
        }
        return null;
    }
}

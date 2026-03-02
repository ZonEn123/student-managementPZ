package com.example.studentmanagement.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

/**
 * JWT工具类：生成、校验、解析token
 */
public class JwtUtil {

    // 签名密钥（生产环境请放到配置文件，不要硬编码）
    private static final String SECRET_KEY = "your-custom-secret-key-1234567890";
    // token过期时间：2小时（单位：毫秒）
    private static final long EXPIRE_TIME = 2 * 60 * 60 * 1000L;

    /**
     * 生成JWT token
     */
    public static String generateToken(Long userId, String username) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        return JWT.create()
                .withClaim("userId", userId)
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .sign(algorithm);
    }

    /**
     * 校验token是否有效
     */
    public static boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            // token过期、签名错误、格式非法均返回false
            return false;
        }
    }

    /**
     * 解析token中的用户信息
     */
    public static DecodedJWT getTokenInfo(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }
}
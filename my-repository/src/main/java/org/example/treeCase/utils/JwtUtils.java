package org.example.treeCase.utils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class JwtUtils {
    // 密钥
    private final static String SECRET_KEY = "2296eaed3d8143f4bd520f06491a0911";
    // 过期时间
    private final static long TOKEN_EXPIRE_MILLIS = 1000 * 60 * 60 * 24 * 7;

    // 创建token
    public static String createToken(Map<String, Object> claimMap) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + TOKEN_EXPIRE_MILLIS))
                .addClaims(claimMap)
                .signWith(generateKey())
                .compact();
    }

    // 验证token
    public static boolean verifyToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(token);
            return false;
        } catch (JwtException e) {
            return true;
        }
    }

    // 解析token
    public static Map<String, Object> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(generateKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 生成安全密钥
    public static Key generateKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }
}

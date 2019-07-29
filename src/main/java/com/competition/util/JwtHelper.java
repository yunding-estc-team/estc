package com.competition.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64UrlCodec;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author asparagusfern
 * token 工具
 */

public class JwtHelper {
    /**
     * token过期时间
     * 签名
     */
    private static final long TOKEN_EXPIRED_TIME = 60 * 60;

    private static final String TOKEN_SECRET = "asparagus";

    /**
     * 获取token(通过userId)
     *
     * @param userId 需要加密的id
     * @return 加密后的token字符串
     */
    public static String getToken(String userId) {
        Map<String,Object> map = new HashMap<>(1);
        map.put("userId",userId);

        return generateToken(map);
    }

    /**
     * 解析token
     *
     * Claims 实现了Map接口可以直接使用
     * @param token 待解析的字符串
     * @return 自定义的声明(Claims)
     */
    public static Map<String,Object> parserToken(String token){
        //签名key
        SecretKey secretKey = getSecretKey();

        Claims claims;
        try{
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token).getBody();
        }
        catch (JwtException ex){
            claims = null;
        }

        return claims;
    }

    /**
     * 生成token
     * @param customClaims 自定义声明
     * @return token字符串
     */
    private static String generateToken(Map<String,Object> customClaims)  {

        //设置签发,到期时间
        long current = System.currentTimeMillis();
        Date now = new Date(current);
        Date expired = new Date(current+TOKEN_EXPIRED_TIME);

        //签名key
        SecretKey secretKey = getSecretKey();

        //设置tokenId防止使用token重复提交
        return Jwts.builder()
                .setClaims(customClaims)
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .setIssuedAt(now)
                .setExpiration(expired)
                .setId(UUID.randomUUID().toString())
                .compact();
    }

    /**
     * 获取SecretKey
     *
     * 使用BASE64URL,AES加密
     * @return TOKEN_SECRET加密key
     */
    private static SecretKey getSecretKey(){
        byte[] encodeKey = Base64UrlCodec.BASE64URL.decode(TOKEN_SECRET);
        return new SecretKeySpec(encodeKey,"AES");
    }


}
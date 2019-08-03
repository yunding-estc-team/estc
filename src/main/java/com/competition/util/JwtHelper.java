package com.competition.util;

import com.competition.VO.TokenObjectVO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.Base64UrlCodec;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * @author guohaodong
 * token 工具
 */

public class JwtHelper {
    /**
     * token过期时间
     * 签名
     */
    private static final long TOKEN_EXPIRED_TIME = 60 * 60 *1000;

    private static final String TOKEN_SECRET = "asparagus";

    /**
     * 解析token
     *
     * Claims 实现了Map接口可以直接使用
     * @param token 待解析的字符串
     * @return 自定义的声明(Claims)
     */
    public static Claims parserToken(String token){
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
     * 获取token中的用户信息
     */
    public static TokenObjectVO getTokenInfo(String token) {
        Claims claims = parserToken(token);
        // 将claim中的数据存入objectVO
        TokenObjectVO objectVO = new TokenObjectVO();
        assert claims != null;
        objectVO.setId(claims.getId());
        objectVO.setType(claims.getSubject());
        return objectVO;
    }
    /**
     * 设置token过期时间
     */
    public static void setTokenExpiredTime(String token,Date date){
        Claims claims = parserToken(token);
        claims.setExpiration(date);
    }

    /**
     * 生成token
     * @param objectVO token所携带的对象
     * @return token字符串
     */
    public static String generateToken(TokenObjectVO objectVO)  {

        //设置签发,到期时间
        long current = System.currentTimeMillis();
        Date now = new Date(current);
        Date expired = new Date(current+TOKEN_EXPIRED_TIME);

        //签名key
        SecretKey secretKey = getSecretKey();

        //设置tokenId(用户id)防止使用token重复提交
        return Jwts.builder()
				.setId(objectVO.getId())
                .setSubject(objectVO.getType())
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .setIssuedAt(now)
                .setExpiration(expired)
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
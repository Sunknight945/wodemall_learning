package com.macro.mall.tiny.common.utils;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 *
 * @author ovo
 */

@Component
public class JwtTokenUtil {
  private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
  private static final String CLAIM_KEY_USERNAME = "sub";
  private static final String CLAIM_KEY_CREATED = "sub";
  
  @Value("${jwt.secret}")
  private String secret;
  
  @Value("${jwt.expiration}")
  private Long expiration;
  
  
  /**
   * 根据负责生成JWT的token
   * 用jwts 构建出一个包含用户信息 和 token自身过期时间 与 签名的 token(json web token)
   *
   * .compact(); 最后  实际上是根据以下内容构建JWT并将其序列化为紧凑的，URL安全的字符串
   *
   * @param claims (要求 存放 username 等)
   * @return JWT的token
   */
  private String generateToken(Map<String, Object> claims) {
    return Jwts.builder()
               .setClaims(claims)
               .setExpiration(generateExpirationDate())
               .signWith(SignatureAlgorithm.HS512, secret)
               .compact();
  }
  
  /**
   * 从token中获取jwt中的负载(载荷)
   * @param token
   * @return
   */
  private Claims getClaimsFromToken(String token) {
    Claims claims = null;
    try {
      claims = Jwts.parser()
                   .setSigningKey(secret)
                   .parseClaimsJws(token)
                   .getBody();
    } catch (Exception e) {
      
      LOGGER.info("jwt格式验证失败:{}", token);
    }
    return claims;
  }
  
  /**
   * 生成token的过期时间
   *
   * @return 返回一个date数据的过期时刻
   */
  private Date generateExpirationDate() {
    return new Date(System.currentTimeMillis() + expiration * 1000);
  }
  
  /**
   * 从token中获取登录用户名
   *
   * @param token
   * @return
   */
  public String getUserNameFromToken(String token) {
    String username;
    try {
      Claims claims = getClaimsFromToken(token);
      username = claims.getSubject();
    } catch (Exception e) {
      username = null;
      LOGGER.info("未获取到当前token:{}", token);
    }
    return username;
  }
  
  /**
   * 校验token是否还有效 (包括是否已过期)
   *
   * @param token
   * @param userDetails
   * @return
   */
  public boolean validateToken(String token, UserDetails userDetails) {
    String username = getUserNameFromToken(token);
    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }
  
  /**
   * @param token jwt
   * @return 判断token是否已过期
   */
  private boolean isTokenExpired(String token) {
    //得到过期时刻 利用
    Date expireDate = getExpiredDateFromToken(token);
    //测试此日期是否在指定日期之前
    return expireDate.before(new Date());
  }
  
  /**
   * 从token中获取过期时间 (时刻)
   *
   * @param token token
   * @return 返回过期时刻
   */
  private Date getExpiredDateFromToken(String token) {
    Claims claims = getClaimsFromToken(token);
    return claims.getExpiration();
  }
  
  /**
   * 根据用户信息生成token
   */
  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
    claims.put(CLAIM_KEY_CREATED, new Date());
    return generateToken(claims);
  }
  
  /**
   * 判断token是否可以被刷新
   *
   * @param token
   * @return 判断是否可以刷新
   */
  public boolean canRefresh(String token) {
    return !isTokenExpired(token);
  }
  
  /**
   * 刷新token
   * @param token
   * @return
   */
  public String refreshToken(String token) {
    Claims claims = getClaimsFromToken(token);
    //更新 CLAIM_KEY_CREATED 过期时间
    claims.put(CLAIM_KEY_CREATED, new Date());
    return generateToken(claims);
  }
  
}
 


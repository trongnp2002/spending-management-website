package com.group6.moneymanagementbooking.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.group6.moneymanagementbooking.model.TokenPayload;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.group6.moneymanagementbooking.enity.Account;

@Component
public class JwtTokenUtil {
  private static final String secret = "TRONG_DZ";

  public static String generateToken(Account account, long expiredDate) {
    Map<String, Object> claims = new HashMap<>();
    TokenPayload tokenPayload = TokenPayload.builder().accountId(account.getId()).email(account.getEmail()).build();
    claims.put("payload", tokenPayload);
    return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + expiredDate * 1000))
        .signWith(SignatureAlgorithm.HS512, secret).compact();

  }

  public static TokenPayload getTokenPayLoad(String token) {

    return getClamsFromToken(token, (Claims claim) -> {

      Map<String, Object> result = (Map<String, Object>) claim.get("payload");
      return TokenPayload.builder().accountId((int) result.get("userId")).email(result.get("email").toString()).build();

    });
  }

  private static <T> T getClamsFromToken(String token, Function<Claims, T> clamResovel) {
    final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    return clamResovel.apply(claims);
  }

  public static boolean validate(String token, Account account) {
    TokenPayload tokenPayload = getTokenPayLoad(token);
    return tokenPayload.getAccountId() == account.getId() && tokenPayload.getEmail().equals(account.getEmail())
        && !isTokenExpired(token);
  }

  private static boolean isTokenExpired(String token) {
    Date expiredDate = getClamsFromToken(token, Claims::getExpiration);
    return expiredDate.before(new Date());
  }
 }

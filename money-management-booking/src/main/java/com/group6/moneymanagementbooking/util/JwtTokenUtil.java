// package com.group6.moneymanagementbooking.util;



// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.function.Function;

// import org.springframework.stereotype.Component;

// import com.group6.moneymanagementbooking.enity.User;
// import com.group6.moneymanagementbooking.model.TokenPayload;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;


// @Component
// public class JwtTokenUtil {
//   private final static String secret = "TRONG_DZ";

//   public static String generateToken(User user, Long expiredDate) {
//     Map<String, Object> claims = new HashMap<>();
//     TokenPayload tokenPayload = TokenPayload.builder().userId(user.getId()).email(user.getEmail()).build();
//     claims.put("payload", tokenPayload);
//     return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
//         .setExpiration(new Date(System.currentTimeMillis() + expiredDate * 1000))
//         .signWith(SignatureAlgorithm.HS512, secret).compact();

//   }

//   public static TokenPayload getTokenPayLoad(String token) {

//     return getClamsFromToken(token, (Claims claim) -> {

//       Map<String, Object> result = (Map<String, Object>) claim.get("payload");
//       return TokenPayload.builder().userId((int) result.get("userId")).email(result.get("email").toString()).build();

//     });
//   }

//   private static <T> T getClamsFromToken(String token, Function<Claims, T> clamResovel) {
//     final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//     return clamResovel.apply(claims);
//   }

//   public static boolean validate(String token, User user) {
//     TokenPayload tokenPayload = getTokenPayLoad(token);
//     return tokenPayload.getUserId() == user.getId() && tokenPayload.getEmail().equals(user.getEmail())
//         && !isTokenExpired(token);
//   }

//   private static boolean isTokenExpired(String token) {
//     Date expiredDate = getClamsFromToken(token, Claims::getExpiration);
//     return expiredDate.before(new Date());
//   }
// }

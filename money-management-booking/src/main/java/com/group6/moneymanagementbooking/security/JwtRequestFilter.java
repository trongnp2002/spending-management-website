// package com.group6.moneymanagementbooking.security;


// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import com.group6.moneymanagementbooking.enity.User;
// import com.group6.moneymanagementbooking.model.TokenPayload;
// import com.group6.moneymanagementbooking.repository.UserRepository;
// import com.group6.moneymanagementbooking.util.JwtTokenUtil;

// import io.jsonwebtoken.ExpiredJwtException;
// import io.jsonwebtoken.SignatureException;
// import lombok.RequiredArgsConstructor;

// @Component
// @RequiredArgsConstructor
// public class JwtRequestFilter extends OncePerRequestFilter {

//     private final UserRepository userRepository;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {
//         final String requestTokenHeader = request.getHeader("Authorization");

//         String token = "";
//         TokenPayload tokenPayload = null;

//         if (requestTokenHeader != null && requestTokenHeader.startsWith("Token ")) {
//             token = requestTokenHeader.substring(6).trim();
//             try {
//                 tokenPayload = JwtTokenUtil.getTokenPayLoad(token);
//             } catch (SignatureException se) {
//                 System.out.println("Invalid JWT signature");
//             } catch (IllegalArgumentException illae) {
//                 System.out.println("Unable to get jwt");
//             } catch (ExpiredJwtException exe) {
//                 System.out.println("Token has expired");
//             }
//         } else {
//             System.out.println("JWT token does not start with 'Token'");
//         }
//         if (tokenPayload != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//             Optional<User> userOptional = userRepository.findById(tokenPayload.getUserId());

//             if (userOptional.isPresent()) {
//                 User user = userOptional.get();
//                 if (JwtTokenUtil.validate(token, user)) {
//                     List<SimpleGrantedAuthority> authority = new ArrayList<>();
//                     UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(),
//                             user.getPassword(), authority);
//                     UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                             userDetails, null, authority);
//                     SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//                 }
//             }
//         }
//         filterChain.doFilter(request, response);

//     }

// }
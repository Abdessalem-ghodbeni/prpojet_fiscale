package com.backendfiscale.backendfiscale.JWTConfiguration;

import com.backendfiscale.backendfiscale.Services.Iservices.IJWTServices;
import com.backendfiscale.backendfiscale.Services.Iservices.IUserServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Slf4j
@Component
//@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final IJWTServices jwtService;
    private final IUserServices userService;
    public JwtAuthenticationFilter(IJWTServices jwtService, IUserServices userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        System.out.println("JWT Filter is running...");
//
//        final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        final String userEmail;
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        jwt = authHeader.substring(7);
//        userEmail = jwtService.extractUsername(jwt);
//        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
//            if (userDetails != null) {
//
//                System.out.println("User: " + userEmail);
//                System.out.println("Authorities:  " + userDetails.getAuthorities());
//            }
//
//            if (jwtService.isTokenValid(jwt, userDetails)) {
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        null,
//                        userDetails.getAuthorities()
//                );
//                authToken.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(request)
//                );
//                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
//                securityContext.setAuthentication(authToken); // Voici la ligne ajoutée pour définir l'authentification
//                SecurityContextHolder.setContext(securityContext);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    System.out.println("🚀 JwtAuthenticationFilter is running!");

    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String userEmail;

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        System.out.println("❌ No Bearer token found, skipping authentication.");
        filterChain.doFilter(request, response);
        return;
    }

    jwt = authHeader.substring(7);
    userEmail = jwtService.extractUsername(jwt);

    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        System.out.println("🔍 Extracted email: " + userEmail);

        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
        System.out.println("👤 Loaded user details: " + userDetails.getUsername());

        if (jwtService.isTokenValid(jwt, userDetails)) {
            System.out.println("✅ JWT is valid. Setting authentication.");

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } else {
            System.out.println("❌ Invalid JWT token.");
        }
    }

    filterChain.doFilter(request, response);
}

}
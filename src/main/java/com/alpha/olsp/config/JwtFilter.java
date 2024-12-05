package com.alpha.olsp.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Extract Authorization header
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Remove "Bearer " prefix

            try {
                // Extract claims from token
                Claims claims = jwtService.getClaims(token);
                String username = claims.getSubject();
                String role = claims.get("role", String.class); // Extract role from claims (if present)

                // Validate claims
                if (username != null && role != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    logger.info("User {} is authenticated", username);
                    logger.info("Role {} is authenticated", role);
                    // Validate and map role
                    String validRole = mapRole(role);
                    if (validRole == null) {
                        throw new IllegalArgumentException("Invalid role in token");
                    }

                    // Create authorities
                    Collection<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(validRole));

                    // Set authentication in the SecurityContext
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            authorities
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    // Logging for debug purposes
                    logger.info("Authenticated user: {} with roles: {}", username, authorities);
                }

            } catch (Exception e) {
                logger.error("Failed to process JWT: {}", e.getMessage());
            }
        } else {
            logger.debug("No valid Authorization header found.");
        }

        // Proceed with the filter chain
        filterChain.doFilter(request, response);
    }

    // Helper to validate and map roles
    private String mapRole(String role) {
        logger.debug("Role: {}", role);
        switch (role) {
            case "ROLE_ADMIN":
            case "ROLE_SELLER":
            case "ROLE_CUSTOMER":
                return role;
            default:
                return null; // Invalid role
        }
    }
}
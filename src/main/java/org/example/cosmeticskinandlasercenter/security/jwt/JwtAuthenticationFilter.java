package org.example.cosmeticskinandlasercenter.security.jwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cosmeticskinandlasercenter.security.service.UserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsServiceImpl userDetailsService;

    /**
     * Intercepts each request to check for JWT in the header, validate it, and set authentication in SecurityContext.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param filterChain FilterChain
     * @throws ServletException if servlet exception occurs
     * @throws IOException if I/O exception occurs
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request); // Extract JWT from request header
            if (StringUtils.hasText(jwt) && jwtTokenProvider.validateJwtToken(jwt)) { // Validate JWT token
                String username = jwtTokenProvider.getUsernameFromJwtToken(jwt); // Get username from token
                UserDetails userDetails = userDetailsService.loadUserByUsername(username); // Load user details

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities() // Create authentication token
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Set request details
                SecurityContextHolder.getContext().setAuthentication(authentication); // Set authentication in SecurityContext
            }
        } catch (Exception e) {
            log.error("Cannot set user authentication: {}", e); // Log authentication errors
        }

        filterChain.doFilter(request, response); // Continue filter chain
    }

    /**
     * Extracts JWT token from the Authorization header.
     * @param request HttpServletRequest
     * @return JWT token string or null if not found or not in Bearer format
     */
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Extract token after "Bearer " prefix
        }
        return null; // No JWT found in header
    }
}
package org.ashe.kappa.auth.conf;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.ashe.kappa.auth.model.User;
import org.ashe.kappa.auth.service.JwtService;
import org.ashe.kappa.auth.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7); // the length of 'Bearer' plus 'space' is 7
        userEmail = jwtService.extractUsername(jwt);
        // extract email from jwt
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // select email from database
            Optional<User> byEmail = userService.findByEmail(userEmail);
            if (byEmail.isPresent()) {
                User user = byEmail.get();
                // user is exist & jwt is valid
                if (jwtService.isTokenValid(jwt, user)) {
                    // issue token to user
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

        }
        filterChain.doFilter(request, response);
    }
}

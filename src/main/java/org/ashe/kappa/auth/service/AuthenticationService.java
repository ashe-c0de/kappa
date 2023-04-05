package org.ashe.kappa.auth.service;

import lombok.RequiredArgsConstructor;
import org.ashe.kappa.auth.conf.JwtService;
import org.ashe.kappa.auth.model.AuthenticationRequest;
import org.ashe.kappa.auth.model.AuthenticationResponse;
import org.ashe.kappa.auth.model.RegisterRequest;
import org.ashe.kappa.auth.model.User;
import org.ashe.kappa.infra.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public Response register(RegisterRequest request) {
        Optional<User> optional = userService.findByEmail(request.getEmail());
        if (optional.isPresent()) {
//            return Response.no("email taken");
            throw new IllegalArgumentException("email taken");
        }
        User user = new User();
        BeanUtils.copyProperties(request, user);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.save(user);
        // register success, issue token
        var jwtToken = jwtService.generateToken(user);
        return Response.ok(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userService.findByEmail(request.getEmail())
                .orElseThrow();
        // authenticate success, issue token
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}

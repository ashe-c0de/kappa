package org.ashe.kappa.auth.controller;

import lombok.RequiredArgsConstructor;
import org.ashe.kappa.auth.model.AuthenticationRequest;
import org.ashe.kappa.auth.model.AuthenticationResponse;
import org.ashe.kappa.auth.model.RegisterRequest;
import org.ashe.kappa.auth.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/access")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }

}

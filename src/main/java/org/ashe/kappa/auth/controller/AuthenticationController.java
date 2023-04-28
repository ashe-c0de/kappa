package org.ashe.kappa.auth.controller;

import lombok.RequiredArgsConstructor;
import org.ashe.kappa.auth.model.AuthenticationRequest;
import org.ashe.kappa.auth.model.RegisterRequest;
import org.ashe.kappa.auth.model.VerifyCodeRequest;
import org.ashe.kappa.auth.service.AuthenticationService;
import org.ashe.kappa.infra.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/access")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @GetMapping("/hello")
    public ResponseEntity<String> hello (){
        return ResponseEntity.ok("hello world");
    }

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Response> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/authenticate2")
    public ResponseEntity<Response> authenticate(@RequestBody VerifyCodeRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/verify_code/{email}")
    public ResponseEntity<Response> sendVerifyCode(@PathVariable String email) {
        return ResponseEntity.ok(service.sendVerifyCode(email));
    }

}

package org.ashe.kappa.auth.controller;

import lombok.RequiredArgsConstructor;
import org.ashe.kappa.auth.model.User;
import org.ashe.kappa.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/token")
@RequiredArgsConstructor
public class TokenController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> userList() {
        return ResponseEntity.ok(userService.userList());
    }

}

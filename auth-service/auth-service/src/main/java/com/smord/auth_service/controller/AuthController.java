package com.smord.auth_service.controller;

import com.smord.auth_service.dto.AuthResponse;
import com.smord.auth_service.dto.LoginRequest;
import com.smord.auth_service.dto.RegisterRequest;
import com.smord.auth_service.exception.EmailAlreadyExistException;
import com.smord.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) throws EmailAlreadyExistException {
        authService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        authService.login(request);
        return ResponseEntity.ok(authService.login(request));
    }
}

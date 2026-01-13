package com.smord.auth_service.service;

import com.smord.auth_service.dto.AuthResponse;
import com.smord.auth_service.dto.LoginRequest;
import com.smord.auth_service.dto.RegisterRequest;
import com.smord.auth_service.model.User;
import com.smord.auth_service.repository.UserRepository;
import com.smord.auth_service.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider tokenProvider;

    public void register(RegisterRequest request){
        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        log.info("New User has been registered!");
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role("USER")
                .build();
        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {
        log.info("Attempting to login: ");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            if (!userRepository.existsByEmail(request.getEmail())) {
                throw new RuntimeException("Invalid credentials");
            }

            User user = userRepository.findByEmail(request.getEmail());
            String token = tokenProvider.generateToken(user.getEmail());

            log.info("User logged in successfully: {}", request.getEmail());
            return new AuthResponse(token);

        } catch (BadCredentialsException e) {
            log.error("Invalid credentials for email: {}", request.getEmail());
            throw new BadCredentialsException("Invalid email or password");
        }
    }
}

package com.smord.auth_service.service;

import com.smord.auth_service.dto.AuthResponse;
import com.smord.auth_service.dto.LoginRequest;
import com.smord.auth_service.dto.RegisterRequest;
import com.smord.auth_service.exception.EmailAlreadyExistException;
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

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider tokenProvider;

    public void register(RegisterRequest request) throws EmailAlreadyExistException {
        if (userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistException("Email already exists");
        }
        log.info("New User has been registered!");
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER")
                .build();
        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) throws BadCredentialsException{
        log.info("Attempting to login: ");

        if (!userRepository.existsByEmail(request.getEmail())){
            log.info("user with email {} does not exist", request.getEmail());
            throw new BadCredentialsException("Invalid credentials");
        }

        User user = userRepository.findByEmail(request.getEmail());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            log.warn("Invalid password for email: {}", request.getEmail());
            throw new BadCredentialsException("Invalid email or password");
        }

        String token = tokenProvider.generateToken(user.getEmail());
        log.info("User logged in successfully: {}", request.getEmail());
        return new AuthResponse(token);

    }
}

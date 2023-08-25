package com.example.airbnb.service;

import com.example.airbnb.securityConfig.JwtUtils;
import com.example.airbnb.dto.authRequest.LoginRequest;
import com.example.airbnb.dto.authRequest.UserRegisterRequest;
import com.example.airbnb.dto.authRequest.authRespons.JwtResponse;
import com.example.airbnb.models.User;
import com.example.airbnb.models.enums.Role;
import com.example.airbnb.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public JwtResponse registerUser(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        user.setUserName(userRegisterRequest.getUsername());
        user.setEmail(userRegisterRequest.getEmail());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));

        String password = userRegisterRequest.getPassword();
        if (password.length() < 6) {
            throw new RuntimeException("Password should be minimum 6 ");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new RuntimeException("Password should contain at least one uppercase letter.");
        }

        // Check for '@' symbol in email
        if (!userRegisterRequest.getEmail().contains("@" + "gmail"+".com")) {
            throw new RuntimeException("Email should contain the '@' symbol and gmail.com");
        }
        if (repository.existsByUserName(userRegisterRequest.getUsername())) {
            throw new RuntimeException("Username already exists.");
        }

        user.setPassword(passwordEncoder.encode(password));

        if (repository.existsByEmail(userRegisterRequest.getEmail()))
            throw new RuntimeException("The email " + userRegisterRequest.getEmail() + " is already in use!");


        User savedUser = repository.save(user);
        String token = jwtUtils.generateToken(userRegisterRequest.getEmail());

       return new JwtResponse(
                savedUser.getEmail(),
                token,
                "Created",
                savedUser.getRole()

        );
    }

    public JwtResponse authenticate(LoginRequest loginRequest) {
        User user = repository.findByEmailOrUserName(loginRequest.getEmailOrUserName(), loginRequest.getEmailOrUserName())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtils.generateToken(user.getEmail());
        return new JwtResponse(
                user.getEmail(),
                token,
                "Created",
                user.getRole()
        );
    }
}

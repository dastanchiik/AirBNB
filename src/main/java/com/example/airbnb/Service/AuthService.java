package com.example.airbnb.Service;

import com.example.airbnb.SecurityConfig.JwtUtils;
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
    public void registerUser(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        user.setEmail( userRegisterRequest.getEmail() );
        user.setRole( Role.USER);
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));

        String password = userRegisterRequest.getPassword();
        if (password.length() > 6) {
            throw new RuntimeException("Password should be maximum 6 characters long.");
        }
        if (!Character.isUpperCase(password.charAt(0))) {
            throw new RuntimeException("Password should start with an uppercase letter.");
        }

        user.setPassword(passwordEncoder.encode(password));

        if (repository.existsByEmail(userRegisterRequest.getEmail()))
            throw new RuntimeException("The email " + userRegisterRequest.getEmail() + " is already in use!");

        User savedUser = repository.save(user);
        String token = jwtUtils.generateToken(userRegisterRequest.getEmail());

        new JwtResponse(
                savedUser.getEmail(),
                token,
                "Creat",
                savedUser.getRole()

        );
    }

    public JwtResponse authenticate(LoginRequest loginRequest) {
        User user = repository.findByEmail(loginRequest.getEmail()).orElseThrow(() ->
                new RuntimeException("User with email: " + loginRequest.getEmail() + " not found!"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String token = jwtUtils.generateToken(user.getEmail());
        return new JwtResponse(
                user.getEmail(),
                token,
                "Creat",
                user.getRole()

        );
    }
}

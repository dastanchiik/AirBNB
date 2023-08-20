package com.example.airbnb.api;

import com.example.airbnb.Service.AuthService;
import com.example.airbnb.dto.authRequest.LoginRequest;
import com.example.airbnb.dto.authRequest.UserRegisterRequest;
import com.example.airbnb.dto.authRequest.authRespons.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        try {
            authService.registerUser(userRegisterRequest);
            return ResponseEntity.ok("User registered successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("login")
    @PermitAll
    public JwtResponse performLogin(@RequestBody LoginRequest loginResponse) {
        return authService.authenticate( loginResponse );
    }
}

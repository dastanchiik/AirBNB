package com.example.airbnb.api;

import com.example.airbnb.service.AuthService;
import com.example.airbnb.dto.authRequest.LoginRequest;
import com.example.airbnb.dto.authRequest.UserRegisterRequest;
import com.example.airbnb.dto.authRequest.authRespons.JwtResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        try {
            JwtResponse jwtResponse = authService.registerUser(userRegisterRequest);
            return ResponseEntity.ok(jwtResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> performLogin(@RequestBody LoginRequest loginRequest) {
        try {
            JwtResponse jwtResponse = authService.authenticate(loginRequest);
            return ResponseEntity.ok(jwtResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register-google")
    public ResponseEntity<JwtResponse> registerUserWithGoogle(@RequestBody UserRegisterRequest userRegisterRequest) {
        JwtResponse response = authService.registerUserWithGoogle(userRegisterRequest);
        return ResponseEntity.ok(response);
    }

        @PostMapping("/login/google")
        public String loginWithGoogle(@RequestParam String googleToken) {
            FirebaseAuth auth = FirebaseAuth.getInstance();

            try {
                FirebaseToken decodedToken = auth.verifyIdToken(googleToken);
                String uid = decodedToken.getUid();
                // Вы можете выполнить дополнительные действия после успешной аутентификации
                return "Успешно вошли через Google!";
            } catch (FirebaseAuthException e) {
                // Обработка ошибок
                return "Ошибка при входе через Google.";
            }
        }
    }

}
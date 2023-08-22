package com.example.airbnb.dto.authRequest.authRespons;
import com.example.airbnb.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String email;
    private String token;
    private String message;
    private Role role;
}

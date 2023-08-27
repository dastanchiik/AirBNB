package com.example.airbnb.service;
import com.example.airbnb.dto.response.DecodedGoogleIdToken;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.stereotype.Service;

@Service
public class GoogleAuthService {

    public DecodedGoogleIdToken verifyGoogleIdToken(String googleIdToken) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(googleIdToken);

            DecodedGoogleIdToken result = new DecodedGoogleIdToken();
            result.setEmail(decodedToken.getEmail());
            result.setUserId(decodedToken.getUid());
            result.setValid(true);

            // You can add more fields to the result if needed

            return result;
        } catch (FirebaseAuthException e) {
            // Handle verification failure
            DecodedGoogleIdToken result = new DecodedGoogleIdToken();
            result.setValid(false);
            return result;
        }
    }
}

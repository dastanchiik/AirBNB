package com.example.airbnb.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FireBaseInitializer {
    @Bean
    public void initializeFirebase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("C:\\Users\\dastan\\IdeaProjects\\AirBNB\\airbnb-813c5-firebase-adminsdk-ux7xl-3d8735ce83.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://airbnb-813c5-default-rtdb.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }
}
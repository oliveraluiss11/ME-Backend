package com.jayway.market_express.common.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.jayway.market_express.common.util.EnvironmentUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class FirebaseConfig {
    private final EnvironmentUtil environmentUtil;
    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        String bucketName = environmentUtil.getFirebaseBucketName();
        String serviceAccountKey = environmentUtil.getFirebaseServiceAccountKey();
        FileInputStream serviceAccount = new FileInputStream(serviceAccountKey);
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket(bucketName)
                .build();
        return FirebaseApp.initializeApp(options);

    }

}

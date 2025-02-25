package org.example;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import java.io.FileInputStream;
import java.io.IOException;

public class FirestoreService {
    private Firestore firestore;

    public FirestoreService() throws IOException {
        String credentialsPath = "C:\\Users\\Administrator\\Desktop\\adminbus\\src\\main\\resources\\serviceAccountKey.json";

        FileInputStream serviceAccount = new FileInputStream(credentialsPath);
        FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        firestore = firestoreOptions.getService();
    }

    public Firestore getFirestore() {
        return firestore;
    }
    public void close() throws Exception {
        if (firestore != null) {
            firestore.close();
        }
    }
}
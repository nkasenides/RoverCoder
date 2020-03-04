package uk.ac.uclan.nkasenides.rovercoder.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import java.io.IOException;
import java.util.List;

public class FirebaseUtils {

    public static FirebaseApp initialize() throws RuntimeException, IOException {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .setDatabaseUrl("https://rovercoder.firebaseio.com/")
                .setStorageBucket("rovercoder.appspot.com")
                .build();

        FirebaseApp firebaseApp = null;
        List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
        if(firebaseApps!=null && !firebaseApps.isEmpty()){
            for(FirebaseApp a : firebaseApps){
                if(a.getName().equals(FirebaseApp.DEFAULT_APP_NAME))
                    return a;
            }
            throw new RuntimeException("Invalid app");
        }
        else {
            return FirebaseApp.initializeApp(options);
        }
    }

    /**
     * Verifies a Firebase token.
     * @param token The token.
     * @return Returns the UID of the user of that token.
     */
    public static String verifyToken(String token) {
        FirebaseToken decodedToken = null;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            String uid = decodedToken.getUid();
            return uid;
        } catch (FirebaseAuthException e) {
            return null;
        }
    }

}

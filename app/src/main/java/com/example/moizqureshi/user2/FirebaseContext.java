package com.example.moizqureshi.user2;

import android.app.Application;
import android.content.Intent;

import com.firebase.client.Firebase;

/**
 * Created by moizqureshi on 5/23/16.
 */
public class FirebaseContext extends Application {
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

        Intent intent = new Intent(FirebaseContext.this, StartedService.class);
        startService(intent);
    }
}

package com.lakshan.iterableintegration;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.messaging.FirebaseMessaging;
import com.iterable.iterableapi.IterableApi;

public class IterableIntegrationApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private static final String TAG = "IterableInit";

    @Override
    public void onCreate() {
        super.onCreate();
        initFCM();
        initIterable();
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    private void initFCM() {
        Log.d(TAG, "Initializing FCM...");

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.e(TAG, "FCM token retrieval failed", task.getException());
                        return;
                    }

                    // Get new FCM registration token
                    String token = task.getResult();
                    Log.d(TAG, "FCM Token: " + token);

                    // Register token with Iterable SDK
                    IterableApi.getInstance().registerDeviceToken(token);
                    Log.d(TAG, "FCM token registered with Iterable");
                });
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }

    private void initIterable() {
        try {
            Log.d(TAG, "Initializing Iterable SDK...");

            String apiKey = "API_KEY";

            // Initialize Iterable API
            IterableApi.initialize(this, apiKey);
            Log.d(TAG, "Iterable SDK initialized successfully");

            // Register for push notifications (if needed)
            IterableApi.getInstance().registerForPush();
            Log.d(TAG, "Push notifications registered with Iterable");

        } catch (Exception e) {
            Log.e(TAG, "Error initializing Iterable SDK", e);
        }
    }
}
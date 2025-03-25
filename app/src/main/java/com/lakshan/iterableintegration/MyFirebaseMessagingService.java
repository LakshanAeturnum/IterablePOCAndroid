package com.lakshan.iterableintegration;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.iterable.iterableapi.IterableApi;
import com.iterable.iterableapi.IterableFirebaseMessagingService;

import java.util.Map;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FCMService";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "onMessageReceived: Push notification received");

        // Log the entire message payload
        Log.d(TAG, "RemoteMessage: " + remoteMessage.toString());

        // Log the message data (custom payload)
        Map<String, String> data = remoteMessage.getData();
        if (data != null && !data.isEmpty()) {
            Log.d(TAG, "Message Data: " + data.toString());
        } else {
            Log.d(TAG, "No custom data found in push notification");
        }

        // Handle message with Iterable SDK
        IterableFirebaseMessagingService.handleMessageReceived(this, remoteMessage);
        Log.d(TAG, "Iterable SDK: Message handled successfully");
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);

        Log.d(TAG, "onNewToken: New FCM token generated");
        Log.d(TAG, "Device Token: " + token);

        // Register token with Iterable API
        IterableApi.getInstance().registerDeviceToken(token);
        Log.d(TAG, "Iterable SDK: Device token registered successfully");
    }

}
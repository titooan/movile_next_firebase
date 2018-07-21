package com.titouan.next.firebasemovilenext.fcm

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.titouan.next.firebasemovilenext.notification.NotificationCreator

const val TAG = "FMService"

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String?) {
        Log.i("NEW_TOKEN", token)

        FirebaseMessaging.getInstance().subscribeToTopic("MAIN")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "FCM Message ID: ${remoteMessage.messageId}")
        Log.d(TAG, "FCM Data Message: ${remoteMessage.data}")
        Log.d(TAG, "FCM Message ID: ${remoteMessage.notification}")

        remoteMessage.notification?.let {
            val title = it.title ?: "Default title"
            val body = it.body ?: "Default Body"
            val data = remoteMessage.data

            Log.d(TAG, "FCM Notification Title: $title")
            Log.d(TAG, "FCM Notification Body:$body")
            Log.d(TAG, "FCM Notication Data: $data")

            NotificationCreator.create(this, title, body)
        }
    }
}

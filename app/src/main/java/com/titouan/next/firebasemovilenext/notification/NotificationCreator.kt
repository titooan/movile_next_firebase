package com.titouan.next.firebasemovilenext.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import com.titouan.next.firebasemovilenext.R
import com.titouan.next.firebasemovilenext.view.MainActivity

class NotificationCreator {

    companion object {
        private var notificationManager: NotificationManager? = null

        const val NOTIFY_ID = 1000
        private val VIBRATION = longArrayOf(300, 400, 500, 400, 300)

        private const val CHANNEL_ID = "MovileNext_1"
        private const val CHANNEL_NAME = "MovileNext - Push"
        private const val CHANNEL_DESCRIPTION = "MovileNext - Push - Channel "

        fun create(context: Context, title: String, body: String) {

            if (notificationManager == null) {
                notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                var channel = notificationManager?.getNotificationChannel(CHANNEL_ID)

                if (channel == null) {

                    channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
                    channel.description = CHANNEL_DESCRIPTION
                    channel.enableVibration(true)
                    channel.enableLights(true)
                    channel.vibrationPattern = VIBRATION

                    notificationManager?.createNotificationChannel(channel)
                }
            }

            val intent = MainActivity.getIntent(context)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                    .setContentTitle(title)
                    .setSmallIcon(R.drawable.ic_notifications)
                    .setContentText(body)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(title)
                    .setVibrate(VIBRATION)
                    .setOnlyAlertOnce(true)
                    .setStyle(NotificationCompat.BigTextStyle().bigText(body))

            val notificationApp = builder.build()
            notificationManager?.notify(NOTIFY_ID, notificationApp)
        }
    }
}
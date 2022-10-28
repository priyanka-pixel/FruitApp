package com.example.fruitapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    private val processLater = false
    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
        getSharedPreferences("_", MODE_PRIVATE).edit().putString("fcm_token", token).apply()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "From: ${remoteMessage.from}")

        if (/* Check if data needs to be processed by long running job */ processLater) {
            // scheduleJob()
            Log.d(TAG, "executing schedule job")
        } else {
            // Handle message within 10 seconds
            handleNow(remoteMessage)
        }
    }

    private fun handleNow(remoteMessage: RemoteMessage) {
        val handler = Handler(Looper.getMainLooper())

        showNotification(remoteMessage.rawData.toString(), remoteMessage.data.toString())
        handler.post {
            Toast.makeText(
                baseContext,
                getString(R.string.handle_notification_now),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    // Method to get the custom Design for the display of
    // notification.
    private fun getCustomDesign(
        title: String?,
        message: String?
    ): RemoteViews {
        val remoteViews = RemoteViews(
            applicationContext.packageName, R.layout.notification
        )
        remoteViews.setTextViewText(R.id.title, title)
        remoteViews.setTextViewText(R.id.message, message)
        remoteViews.setImageViewResource(
            R.id.icon,
            R.drawable.ic_launcher_foreground
        )
        return remoteViews
    } // Method to display the notifications

    fun showNotification(
        title: String?,
        message: String?
    ) { // Pass the intent to switch to the MainActivity
        val intent = Intent(this, MainActivity::class.java)
        // Assign channel ID
        val channel_id = "notification_channel"
        // Here FLAG_ACTIVITY_CLEAR_TOP flag is set to clear
        // the activities present in the activity stack,
        // on the top of the Activity that is to be launched
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        // Pass the intent to PendingIntent to start the
        // next Activity
        //  val pendingIntent = PendingIntent.getActivity(
//            this, 0, intent,
//            PendingIntent.FLAG_ONE_SHOT
//        )
        var pendingIntent: PendingIntent? = null
        pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)
        } else {
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        } // Create a Builder object using NotificationCompat
        // class. This will allow control over all the flags
        var builder: NotificationCompat.Builder = NotificationCompat.Builder(
            applicationContext,
            channel_id
        )
            .setSmallIcon(com.google.firebase.installations.R.drawable.notification_bg)
            .setAutoCancel(true)
            .setVibrate(
                longArrayOf(
                    1000, 1000, 1000,
                    1000, 1000
                )
            )
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        // A customized design for the notification can be
        // set only for Android versions 4.1 and above. Thus
        // condition for the same is checked here.
        builder = builder.setContent(
            getCustomDesign(title, message)
        )
        // Create an object of NotificationManager class to
        // notify the
        // user of events that happen in the background.
        val notificationManager = getSystemService(
            NOTIFICATION_SERVICE
        ) as NotificationManager
        // Check if the Android Version is greater than Oreo
        if (Build.VERSION.SDK_INT
            >= Build.VERSION_CODES.O
        ) {
            val notificationChannel = NotificationChannel(
                channel_id, "web_app",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(
                notificationChannel
            )
        }
        notificationManager.notify(0, builder.build())
    }

    companion object {
        private const val TAG = "MyFirebaseMessagingS"
    }
}
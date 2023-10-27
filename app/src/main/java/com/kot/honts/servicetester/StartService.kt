package com.kot.honts.servicetester

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class StartService : Service() {
    private val TAG = "ST-StartService"

    private val CHANNEL_ID = "com.kot.honts.servicetester"
   

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: service onCreate called")

        // this is very important, if startService is not called, then the app throws ANR
        val comp = ComponentName("com.kot.honts.servicetester", StartService::class.java.getName())
        startService(Intent().setComponent(comp))
    }
    
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: called")
        //startRecording();
        createNotificationChannel()


        return super.onStartCommand(intent, flags, startId)
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "android channel"
            val description = "channel description"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = description
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
            showText("Notification")
        }
    }

    private fun showText(text: String) {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.sym_def_app_icon)
            .setContentTitle("Notification")
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        // Set the intent that will fire when the user taps the notification
        val actionpending = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, actionpending, 0)
        builder.setContentIntent(pendingIntent)
        val notificationManager = NotificationManagerCompat.from(this)

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(1236, builder.build())
        startForeground(1, builder.build())

        val activityIntent = Intent(this, MainActivity::class.java)
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(activityIntent)

    }


}
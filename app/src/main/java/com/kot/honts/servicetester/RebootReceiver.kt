package com.kot.honts.servicetester

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat.startForegroundService


class RebootReceiver : BroadcastReceiver() {
    private  val TAG = "ST-RebootReceiver"
    private var componentName: ComponentName? = null
    
    
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d(TAG, "onReceive: broadcast received")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (p0 != null) {
                componentName =
                    ComponentName("com.kot.honts.servicetester", "com.kot.honts.servicetester.StartService")
                startForegroundService(p0,Intent().setComponent(componentName))

            }
        }
    }


}
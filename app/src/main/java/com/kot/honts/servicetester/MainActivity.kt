package com.kot.honts.servicetester

import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    private val TAG = "ST-MainActivity"


    private var startServiceBtn: Button? = null

    private var componentName: ComponentName? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startServiceBtn = findViewById(R.id.startServiceButton)
        startServiceBtn?.setOnClickListener(View.OnClickListener {
            componentName =
                ComponentName("com.kot.honts.servicetester", "com.kot.honts.servicetester.StartService")
            //startForegroundService(Intent().setComponent(componentName))
            stopService(Intent().setComponent(componentName))
        })
        

    }
}
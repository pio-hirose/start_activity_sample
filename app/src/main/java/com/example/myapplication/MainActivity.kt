package com.example.myapplication

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnThisActivityStart: Button = findViewById(R.id.start_this_activity)
        btnThisActivityStart.setOnClickListener {
            val serviceIntent = Intent(this, TestService::class.java)
            serviceIntent.action = "start_this_activity"
            startForegroundService(serviceIntent)
        }

        val btnYoutubeStart: Button = findViewById(R.id.start_youtube)
        btnYoutubeStart.setOnClickListener {
            val serviceIntent = Intent(this, TestService::class.java)
            serviceIntent.action = "start_youtube"
            startForegroundService(serviceIntent)
        }
    }


    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }
}
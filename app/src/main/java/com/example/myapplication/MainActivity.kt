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
        Log.d("test", "${this.javaClass.name}: onCreate")
        setContentView(R.layout.activity_main)

        val btnThisActivityStart: Button = findViewById(R.id.start_this_activity)
        btnThisActivityStart.setOnClickListener {
            Intent(this, TestService::class.java).apply {
                this.action = "start_this_activity"
                startForegroundService(this)
            }
            Log.d("test", "${this.javaClass.name}: start Service for MainActivity")
        }

        val btnYoutubeStartFromPackage: Button = findViewById(R.id.start_youtube_from_package)
        btnYoutubeStartFromPackage.setOnClickListener {
            Intent(this, TestService::class.java).apply {
                this.action = "start_youtube_from_package"
                startForegroundService(this)
            }
            Log.d("test", "${this.javaClass.name}: start Service for Youtube from package")

        }

        val btnYoutubeStartFromDeepLink: Button = findViewById(R.id.start_youtube_from_deeplink)
        btnYoutubeStartFromDeepLink.setOnClickListener {
            Intent(this, TestService::class.java).apply {
                this.action = "start_youtube_from_deeplink"
                startForegroundService(this)
            }
            Log.d("test", "${this.javaClass.name}: start Service for Youtube from deeplink")

        }
    }


    override fun onResume() {
        super.onResume()
        Log.d(TAG, "${this.javaClass.name}: onResume")
    }

    override fun onPause() {
        Log.d(TAG, "${this.javaClass.name}: onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "${this.javaClass.name}: onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "${this.javaClass.name}: onDestroy")
        super.onDestroy()
    }
}
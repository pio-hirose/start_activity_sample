package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("test", "alarm receiver")

        when (intent?.action) {
            "start_this_activity" -> {
                Intent(context, MainActivity::class.java).apply {
                    this.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    if (context != null) {
                        context.startActivity(this)
                        Log.d("test", "start MainActivity")
                    } else {
                        Log.d("test", "can not start MainActivity")
                    }
                }
            }
            "start_youtube_from_package" -> {
                val packageName = "com.google.android.youtube"
                context?.packageManager?.getLaunchIntentForPackage(packageName).apply {
                    if (context != null) {
                        Log.d("test", "start youtube from package")
                        context.startActivity(this)
                    } else {
                        Log.d("test", "can not start youtube from package")
                    }
                }
            }
            "start_youtube_from_deeplink" -> {
                val uri = Uri.parse("vnd.youtube://")
                Intent(Intent.ACTION_VIEW, uri).apply {
                    this.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    if (context != null && this.resolveActivity(context.packageManager) != null) {
                        context.startActivity(this)
                        Log.d("test", "start youtube from deeplink")
                    } else {
                        Log.d("test", "can not start youtube from deeplink")
                    }

                }
            }
            else -> {
                Log.d("test", "undefined action")
            }
        }
    }
}
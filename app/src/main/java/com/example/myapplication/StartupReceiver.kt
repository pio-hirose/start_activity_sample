package com.example.myapplication

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log

class StartupReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("test", "${this.javaClass.name}: onReceive")
        if (intent != null && intent.action == "android.intent.action.BOOT_COMPLETED") {
            /*
            Intent(context, MainActivity::class.java).apply {
                this.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(this)
            }
            Log.d("test", "start MainActivity")

             */

            /*
            val uri = Uri.parse("vnd.youtube://")
            Intent(Intent.ACTION_VIEW, uri).apply {
                this.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                if (this.resolveActivity(context.packageManager) != null) {
                    context.startActivity(this)
                    Log.d("test", "start youtube from deeplink")
                } else {
                    Log.d("test", "can not start youtube from deeplink")
                }
            }

             */

            /*
            val packageName = "com.google.android.youtube"
            context.packageManager?.getLaunchIntentForPackage(packageName).apply {
                Log.d("test", "start youtube from package")
                context.startActivity(this)
            }

             */

            Intent(context, TestService::class.java).apply {
                this.action = "start_this_activity"
                context.startForegroundService(this)
            }
            Log.d("test", "${this.javaClass.name}: start Service for MainActivity")


        }
    }
}
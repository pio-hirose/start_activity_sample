package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("test", "alarm receiver")

        if (intent?.action == "start_this_activity") {
            val startIntent: Intent = Intent(context, MainActivity::class.java)
            //起動するアクティビティを取得
            startIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context!!.startActivity(startIntent)
        } else {
            val packageName = "com.google.android.youtube"
            val startIntent: Intent? = context?.packageManager?.getLaunchIntentForPackage(packageName)
            if (startIntent != null) {
                Log.d("test", "start youtube")
                context.startActivity(startIntent)
            } else {
                //アプリがインストールされていない場合
                Log.d("test", "can not start youtube")
            }
        }

    }
}
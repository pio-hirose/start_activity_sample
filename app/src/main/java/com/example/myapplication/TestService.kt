package com.example.myapplication

import android.app.*
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_MUTABLE
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class TestService : Service() {
    companion object {
        const val CHANNEL_ID = "1111"
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("Service","onStartCommand called")

        //1．通知領域タップで戻ってくる先のActivity
        val openIntent = Intent(this, MainActivity::class.java).let {
            PendingIntent.getActivity(this, 0, it, FLAG_IMMUTABLE)
        }

        //2．通知チャネル登録
        val channelId = CHANNEL_ID
        val channelName = "TestService Channel"
        val channel = NotificationChannel(
            channelId, channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)

        //3．ブロードキャストレシーバーをPendingIntent化

        val sendIntent = Intent(this, MainActivity::class.java).apply {
            action = Intent.ACTION_SEND
            setFlags(FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
        }
        //val sendPendingIntent = PendingIntent.getBroadcast(this, 0, sendIntent, FLAG_IMMUTABLE)
        val notifyPendingIntent = PendingIntent.getActivity(
            this, 0, sendIntent, FLAG_IMMUTABLE
        )
        //4．通知の作成（ここでPendingIntentを通知領域に渡す）
        val notification = NotificationCompat.Builder(this, CHANNEL_ID )
            .setSmallIcon(androidx.customview.R.drawable.notification_bg)
            .setContentTitle("フォアグラウンドのテスト中")
            .setContentText("起動する場合はこちらから行って下さい。")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(openIntent)
            .addAction(R.drawable.ic_launcher_foreground, "アプリを起動", notifyPendingIntent)
            .build()

        //5．フォアグラウンド開始。
        startForeground(2222, notification)

        Thread {
            Thread.sleep(10000)

            Log.d("test", "set alarm")
            // 実行する時間（ミリ秒）
            val alarmTime = System.currentTimeMillis()

            // 実行したいクラスから Intent を作成
            val alarmIntent = Intent(this, AlarmReceiver::class.java)
            alarmIntent.action = intent?.action
            val pendingIntent = PendingIntent.getBroadcast(this, 1, alarmIntent, PendingIntent.FLAG_IMMUTABLE)

            // AlarmManager で pendingIntent を指定時間後に実行するように設定
            val alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent)
        }.start()

        return START_STICKY
    }

    override fun stopService(name: Intent?): Boolean {
        return super.stopService(name)
    }

}
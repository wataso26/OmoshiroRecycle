package app.wataso_.watanabe.omoshirorecycle

import android.R
import android.app.LauncherActivity.ListItem
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat


class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        // Get id
        val notificationId = intent.getIntExtra("notificationId", 0)
        val mainIntent = Intent(context, TimePickerDialogActivity::class.java)
        val contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0)

        // 通知マネジャー
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channelName: CharSequence = "My Notification"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, channelName, importance)
            notificationManager.createNotificationChannel(channel)
        }

        // 内容
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_dialog_info)
                .setContentTitle("お元気ですか?")
                .setContentText("過去の面白かった事を振り返ってみませんか？")
                .setContentIntent(contentIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)

        // 通知をセットする
        notificationManager.notify(notificationId, builder.build())

    }

    companion object {
        private const val CHANNEL_ID = "CHANNEL_SAMPLE"
    }

}
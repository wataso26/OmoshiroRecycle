package app.wataso_.watanabe.omoshirorecycle

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaParser
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import androidx.media.app.NotificationCompat

class SoundManagerService : Service() {

    companion object{
        //通知チャンネルID文字列定数
        private const val CHANEL_ID = "soundmanagerservice_notification_channel"
    }
    private var _player: MediaPlayer? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        _player = MediaPlayer()
        //通知チャンネル名をstrings.xmlから取得
        val name = "サービスサンプル通知"
        //通知チャンネルの重要度を標準に設定する
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        //通知チャンネルを生成
        val channel  = NotificationChannel(CHANEL_ID,name,importance)
        //notificaionmangerオブジェクトを取得
        val manager =getSystemService(NotificationManager::class.java)

        manager.createNotificationChannel(channel)

    }
    //時間になった時に通知が来るようにする privateのやつは変える
    private inner class PlayCompletionListener :MediaPlayer.OnCompletionListener{
        override fun onCompletion(mp:MediaPlayer){
            //builderクラスを作成する
            //val builder =NotificationCompat.Builder(this@SoundManagerService, CHANEL_ID)
            //通知エリアに表示されるアイコンを設定
            //builder.setSmallIcon(R.drawable.ic_dialog_info)
            //通知ドロワーでの表示タイトルを設定
            //builder.setContentTitle("お知らせ")
            //通知ドロワーでの表示メッセージを設定
            //builder.setContentText("思い出を振り返りませんか？")
            //builderからnotificationオブジェクト
            //val notification = builder.build()
            //notificationmanagercompatオブジェクトを取得
            val manager = NotificationManagerCompat.from(this@SoundManagerService)
            //通知
            //manager.notify(100,notification)
            //仮　音楽が止まる
            stopSelf()
        }

    }



    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
package app.wataso_.watanabe.omoshirorecycle

import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.setting.*
import java.text.SimpleDateFormat
import java.util.*

class TimePickerDialogActivity : AppCompatActivity() {

    lateinit var yobi_ad: Button
    private val notificationId = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting)
        //一応
        yobi_ad = findViewById<Button>(R.id.yobi_ad)

        //EditTextのクリックイベントを設定(タイムピッカー)
        button7.setOnClickListener {
            showTimePickerDialog()

        }
        //yobi_adがクリックされた時にアラートダイアログが出るようにする
        yobi_ad.setOnClickListener {
            var dialog = CustomDialogFragment()

            dialog.show(supportFragmentManager, "customDialog")
        }
        //通知設定

        // Intent
        val intent = Intent(this@TimePickerDialogActivity, AlarmReceiver::class.java)
        intent.putExtra("notificationId", notificationId)

        // PendingIntent

        // PendingIntent
        val pendingIntent = PendingIntent.getBroadcast(
                this@TimePickerDialogActivity, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT
        )

    }

    //時間ダイアログを開くためのメソッド
    fun showTimePickerDialog() {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            //EditTextに選択された時間を設定する処理
            textView5.setText(SimpleDateFormat("HH:mm").format(cal.time))

        }

        //タイムピッカーダイアログを生成
        TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
    }


}
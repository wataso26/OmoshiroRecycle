package app.wataso_.watanabe.omoshirorecycle

import android.app.AlertDialog
import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.setting.*
import java.text.SimpleDateFormat
import java.util.*

class TimePickerDialogActivity : AppCompatActivity() {

    lateinit var text_et: EditText
    lateinit var yobi_ad: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting)

        text_et = findViewById<EditText>(R.id.text_et) //一応
        yobi_ad = findViewById<Button>(R.id.yobi_ad)

        //EditTextのクリックイベントを設定(タイムピッカー)
        text_et.setOnClickListener {
            showTimePickerDialog()

        }
        //yobi_adがクリックされた時にアラートダイアログが出るようにする
        yobi_ad.setOnClickListener {
            var dialog = CustomDialogFragment()

            dialog.show(supportFragmentManager,"customDialog")
        }

    }

    //時間ダイアログを開くためのメソッド
    fun showTimePickerDialog() {
        val calendar: Calendar = Calendar.getInstance()

        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            //EditTextに選択された時間を設定する処理
            text_et.setText(SimpleDateFormat("HH:mm").format(cal.time))
        }

        //タイムピッカーダイアログを生成
        TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
    }


}
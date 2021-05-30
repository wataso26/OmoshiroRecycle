package app.wataso_.watanabe.omoshirorecycle

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*

class TimePickerDialogActivity : AppCompatActivity() {

    lateinit var text_et: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting)

        text_et = findViewById<EditText>(R.id.text_et) //一応

        //EditTextのクリックイベントを設定
        text_et.setOnClickListener {
            showTimePickerDialog()
        }

    }

    //時間ダーダイアログを開くためのメソッド
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
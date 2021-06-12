package app.wataso_.watanabe.omoshirorecycle

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class TimePickerDialogActivity : AppCompatActivity(), View.OnClickListener {
    private val notificationId = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting)

        // Set onClick Listener
        findViewById<View>(R.id.Set).setOnClickListener(this)
        findViewById<View>(R.id.とりけし).setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val timePicker = findViewById<TimePicker>(R.id.TimeP)

        // Intent
        val intent = Intent(this@TimePickerDialogActivity, AlarmReceiver::class.java)
        intent.putExtra("notificationId", notificationId)

        // PendingIntent
        val pendingIntent = PendingIntent.getBroadcast(
                this@TimePickerDialogActivity, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT
        )

        // AlarmManager
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        when (view.id) {
            R.id.Set -> {
                val hour = timePicker.currentHour
                val minute = timePicker.currentMinute

                // Create time.
                val startTime = Calendar.getInstance()
                startTime[Calendar.HOUR_OF_DAY] = hour
                startTime[Calendar.MINUTE] = minute
                startTime[Calendar.SECOND] = 0
                val alarmStartTime = startTime.timeInMillis

                // アラームをセットする
                alarmManager[AlarmManager.RTC_WAKEUP, alarmStartTime] = pendingIntent
                Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show()
            }
            R.id.とりけし -> {
                alarmManager.cancel(pendingIntent)
                Toast.makeText(this, "Canceled.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package app.wataso_.watanabe.omoshirorecycle

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SubuSetting : AppCompatActivity() {

    lateinit var yobi_ad: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sabu)

        yobi_ad = findViewById<Button>(R.id.SubuSetting)

        //yobi_adがクリックされた時にアラートダイアログが出るようにする
        yobi_ad.setOnClickListener {
            var dialog = CustomDialogFragment()

            dialog.show(supportFragmentManager, "customDialog")
        }
    }
}
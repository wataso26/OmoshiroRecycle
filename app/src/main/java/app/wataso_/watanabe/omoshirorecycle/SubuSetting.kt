package app.wataso_.watanabe.omoshirorecycle

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import app.wataso_.watanabe.omoshirorecycle.sample.CustomDialogFragment
import kotlinx.android.synthetic.main.sabu.*

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
        button10.setOnClickListener{
            AlertDialog.Builder(this)
                    .setMessage("今日学校で、先生をお母さんと呼んでしまった。")
                    .setPositiveButton("戻る", { dialog, which ->
                    })
                    .show()
        }
        button9.setOnClickListener {
            AlertDialog.Builder(this)
                    .setMessage("東京03の〇〇というコント、終わりが意外！")
                    .setPositiveButton("戻る", { dialog, which ->
                    })
                    .show()

        }
        button8.setOnClickListener {
            AlertDialog.Builder(this)
                    .setMessage("イッテQのチーズ祭りが面白かった")
                    .setPositiveButton("戻る", { dialog, which ->
                    })
                    .show()
        }
        button7.setOnClickListener {
            AlertDialog.Builder(this)
                    .setMessage("ねこがねころんだ（オヤジギャグ）")
                    .setPositiveButton("戻る", { dialog, which ->
                    })
                    .show()
        }

    }
}
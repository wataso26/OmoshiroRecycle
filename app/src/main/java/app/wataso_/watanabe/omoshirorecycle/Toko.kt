package app.wataso_.watanabe.omoshirorecycle

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.toko.*

class Toko : AppCompatActivity() {

    val readRequestCode: Int=42
    lateinit var galleryButton: Button

    //genreAlertDialogの設定
    lateinit var genre_ad : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.toko)

        galleryButton.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            galleryIntent.addCategory(Intent.CATEGORY_OPENABLE)
            //タイプを指定している
            galleryIntent.type = "image/*"
            startActivityForResult(galleryIntent, readRequestCode)

            galleryButton = findViewById(R.id.galleryButton)

        }
        //genreAlertDialogの設定
        genre_ad = findViewById<Button>(R.id.genre_ad)
        //genreAlertDialogがボタンが押された時に表示されるようにする
        genre_ad.setOnClickListener {
            var dialog = GenreCustomDialogFragment()

            dialog.show(supportFragmentManager,"customDialog")
        }
    }
    //遷移先のアクティビティから結果を受け取る
    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)

        if (requestCode == readRequestCode && resultCode == Activity.RESULT_OK){
            resultData?.data.also{ uri ->
                imageView.setImageURI(uri)
            }
        }
    }
}
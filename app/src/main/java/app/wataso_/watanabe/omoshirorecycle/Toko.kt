package app.wataso_.watanabe.omoshirorecycle

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import io.realm.Realm
import kotlinx.android.synthetic.main.item_data_cell.*
import kotlinx.android.synthetic.main.toko.*

class Toko : AppCompatActivity() {

    //realmの追加
    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    val readRequestCode: Int=42


    //genreAlertDialogの設定
    lateinit var genre_ad : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.toko)

        //galleryButtonクリック時にギャラリーを開く
        galleryButton.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            galleryIntent.addCategory(Intent.CATEGORY_OPENABLE)
            //タイプを指定している
            galleryIntent.type = "image/*"
            startActivityForResult(galleryIntent, readRequestCode)


        }
        //genreAlertDialogの設定
        genre_ad = findViewById<Button>(R.id.genre_ad)
        //genreAlertDialogがボタンが押された時に表示されるようにする
        genre_ad.setOnClickListener {
            var dialog = GenreCustomDialogFragment()

            dialog.show(supportFragmentManager,"customDialog")

        }

    }
    //遷移先のアクティビティから結果を受け取る 画像取得
    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)

        if (requestCode == readRequestCode && resultCode == Activity.RESULT_OK){
            resultData?.data.also{ uri ->
                imageView.setImageURI(uri)

            }
        }
    }
}
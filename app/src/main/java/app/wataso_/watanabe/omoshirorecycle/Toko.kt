package app.wataso_.watanabe.omoshirorecycle

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import io.realm.Realm
import kotlinx.android.synthetic.main.toko.*

class Toko : AppCompatActivity() {

    //realmの追加
    val realm:Realm =Realm.getDefaultInstance()

    val readRequestCode: Int=42


    //genreAlertDialogの設定
    lateinit var genre_ad : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.toko)

        val task: Task? =read()
        //edittextに取得したデータを入れる
        if (task !=null){
            titleEditText.setText(task.title)
            contentEditText.setText(task.content)
        }
        saveButton.setOnClickListener {
            val title: String = titleEditText.text.toString()
            val content: String = contentEditText.text.toString()
            save(title,content)
        }



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
            val strList = arrayOf("日常","テレビ・コント","フレーズ","写真で一言","フリースタイル")

            AlertDialog.Builder(this) // FragmentではActivityを取得して生成
                    .setTitle("ラジオボタン選択ダイアログ")

                    .setSingleChoiceItems(strList, 0, { dialog, which ->
                        genre_textView.text = strList[0]
                    })
                    .setSingleChoiceItems(strList, 1, { dialog, which ->
                        genre_textView.text = strList[1]
                    })
                    .setSingleChoiceItems(strList, 3, { dialog, which ->
                        genre_textView.text = strList[3]

                    })
                    .setPositiveButton("OK", { dialog, which ->

                    })
                    .show()
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
    //realm画面終了時
    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
    //画面起動時
    fun read(): Task?{
        return realm.where(Task::class.java).findFirst()
    }
    fun save(title:String,content: String){
        //保存機能
        val task:Task? = read()

        realm.executeTransaction {
            if(task !=null){
                task.title=title
                task.content=content
            }else{
                //メモの新規作成
                val newTask: Task =it.createObject(Task::class.java)
                newTask.title =title
                newTask.content =content
            }
        }
    }

}
package app.wataso_.watanabe.omoshirorecycle

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toko.*
import java.util.*

class Toko : AppCompatActivity() {

    //realmの追加
    val realm:Realm =Realm.getDefaultInstance()

    val readRequestCode: Int=42

    //genreAlertDialogの設定
    lateinit var genre_ad : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.toko)

        //最初の説明の解除
        val shr = getSharedPreferences("beginner", Context.MODE_PRIVATE)
        val editor=shr.edit()
        var Number1=shr.getInt("number",0)
        if(Number1==0){
            editor.putInt("number",1)
            editor.apply()
        }
        //最初の使い方の案内
        val shr2 = getSharedPreferences("beginner2", Context.MODE_PRIVATE)
        var Number2=shr2.getInt("number2",0)
        if (Number2==0){

            AlertDialog.Builder(this) // FragmentではActivityを取得して生成
                    .setTitle("保存方法")
                    .setMessage("5つジャンルから選択して、\n内容を入力しよう！")
                    .setPositiveButton("やってみる", { dialog, which ->
                    })
                    .show()

        }

        //edittextに取得したデータを入れる
        saveButton.setOnClickListener {
            val title: String = genre_textView.text.toString()
            val content: String = contentEditText.text.toString()
            save(title,content)
            val toMainActivityIntent = Intent(this,MainActivity::class.java)
            startActivity(toMainActivityIntent)

            val editor=shr2.edit()
            var Number2=shr2.getInt("number2",0)
            if(Number2==0){
                editor.putInt("number2",1)
                editor.apply()
            }
        }

        //galleryButtonクリック時にギャラリーを開く（保留）
        //galleryButton.setOnClickListener {
            //val galleryIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            //galleryIntent.addCategory(Intent.CATEGORY_OPENABLE)
            //タイプを指定している
           // galleryIntent.type = "image/*"
            //startActivityForResult(galleryIntent, readRequestCode)

        //}
        //genreAlertDialogの設定
        genre_ad = findViewById<Button>(R.id.genre_ad)
        //genreAlertDialogがボタンが押された時に表示されるようにする
        genre_ad.setOnClickListener {
            val strList = arrayOf("日常","テレビ","フレーズ","コント","フリースタイル")

            AlertDialog.Builder(this) // FragmentではActivityを取得して生成
                    .setTitle("ラジオボタン選択ダイアログ")

                    .setSingleChoiceItems(strList, 0, { dialog, which ->
                        genre_textView.text=strList[which]
                    })
                    .setPositiveButton("OK", { dialog, which ->
                    })
                    .show()
        }

    }
    //遷移先のアクティビティから結果を受け取る 画像取得 （保留）
    //override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
    //    super.onActivityResult(requestCode, resultCode, resultData)

    //    if (requestCode == readRequestCode && resultCode == Activity.RESULT_OK){
      //      resultData?.data.also{ uri ->
                //imageView.setImageURI(uri)

                //val photo= imageView.setImageURI(uri)
     //       }
     //   }
    //}
    //realm画面終了時
    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
    //画面起動時
    fun read(): Task?{
        return realm.where(Task::class.java).findFirst()
    }
    fun save(title:String,content: String) {
        //保存機能
        val task: Task? = read()
        realm.executeTransaction {
                //メモの新規作成
                val newTask: Task = it.createObject(Task::class.java,UUID.randomUUID().toString())
                newTask.title = title
                newTask.content = content

        }
    }


}
package app.wataso_.watanabe.omoshirorecycle

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
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

        val task: Task? =read()
        //edittextに取得したデータを入れる
        if (task !=null){
            genre_textView.setText(task.title)
            contentEditText.setText(task.content)
        }
        saveButton.setOnClickListener {
            val title: String = genre_textView.text.toString()
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
                        genre_textView.text=strList[which]
                    })
                    .setPositiveButton("OK", { dialog, which ->
                    })
                    .show()
        }
        //保存ボタンを押した時に画面遷移する
        saveButton.setOnClickListener {
            val toMainActivityIntent = Intent(this,MainActivity::class.java)
            startActivity(toMainActivityIntent)
            createDummyData()
        }
        //以下はtaskリサイクルヴューの記述
        val taskList = readAll()


        val adapter =
                TaskAdapter(this, taskList, object : TaskAdapter.OnItemClickListener {
                    override fun onItemClick(item: Task) {
                        // クリック時の処理
                        //Toast.makeText(applicationContext, item.content + "を削除しました", Toast.LENGTH_SHORT).show()
                        //delete(item.id)
                    }
                }, true)



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
    fun createDummyData() {
        create(R.drawable.ic_launcher_background, "","")

    }
    //以下はtaskリサイクルヴューの記述
    fun create(imageId: Int, content: String,title: String) {
        realm.executeTransaction {
            val task = it.createObject(Task::class.java, UUID.randomUUID().toString())
            task.imageId = imageId
            task.content = content
            task.title =title
        }
    }
    fun readAll(): RealmResults<Task> {
        return realm.where(Task::class.java).findAll().sort("createdAt", Sort.ASCENDING)
    }

    //アイテムを削除する方法
    fun update(id: String, content: String,title: String) {
        realm.executeTransaction {
            val task = realm.where(Task::class.java).equalTo("id", id).findFirst()
                    ?: return@executeTransaction
            task.content = content
            task.title =title
        }
    }

    fun update(task: Task, content: String,title: String) {
        realm.executeTransaction {
            task.content = content
            task.title =title
        }
    }

    fun delete(id: String) {
        realm.executeTransaction {
            val task = realm.where(Task::class.java).equalTo("id", id).findFirst()
                    ?: return@executeTransaction
            task.deleteFromRealm()
        }
    }

    fun delete(task: Task) {
        realm.executeTransaction {
            task.deleteFromRealm()
        }
    }

    fun deleteAll() {
        realm.executeTransaction {
            realm.deleteAll()
        }
    }

}
package app.wataso_.watanabe.omoshirorecycle
import android.content.ContentValues.TAG
import android.content.Intent
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.setting.*
import kotlinx.android.synthetic.main.toko.*
import java.net.URI.create
import java.nio.file.Files.delete
import java.util.*


class MainActivity : AppCompatActivity() {
    //realm
    val realm:Realm =Realm.getDefaultInstance()

    //recyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //以下12行はtaskのリサイクラビュー
        val taskList = readAll()

        val adapter =
            TaskAdapter(this, taskList, object : TaskAdapter.OnItemLongClickListener {
                override fun  onItemLongClick(item: Task) {
                    // クリック時の処理
                    Toast.makeText(applicationContext, item.content + "を削除しました", Toast.LENGTH_SHORT).show()
                    delete(item.id)
                }
            }, true)


        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        //投稿ボタンを押した時に画面が遷移する
        tokoButton.setOnClickListener {
            val toTokoActivityIntent = Intent(this,Toko::class.java)
            startActivity(toTokoActivityIntent)
        }
        //以下ジャンル分けの機能
        //一覧ボタンを押した時
        button.setOnClickListener {
            val taskList = readAll()


            val adapter =
                    TaskAdapter(this, taskList, object : TaskAdapter.OnItemLongClickListener {
                        override fun  onItemLongClick(item: Task) {
                            // クリック時の処理
                            Toast.makeText(applicationContext, item.content + "を削除しました", Toast.LENGTH_SHORT).show()
                            delete(item.id)
                        }
                    }, true)
            recyclerView.adapter = adapter

        }

        //日常ボタンを押した時
        button6.setOnClickListener {
            val book = realm.where(Task::class.java)
                    .equalTo("title","日常")
                    .findAll()
            val nichijoadapter =
                    TaskAdapter(this, book, object: TaskAdapter.OnItemLongClickListener {
                        override fun onItemLongClick(item: Task) {
                            // クリック時の処理
                            Toast.makeText(applicationContext, item.content + "を削除しました", Toast.LENGTH_SHORT).show()
                            delete(item.id)
                        }
                    }, true)
            recyclerView.adapter=nichijoadapter
            Log.d(TAG,book.toString())
            }

        //テレビボタンを押した時
        button2.setOnClickListener {
            val book = realm.where(Task::class.java)
                    .equalTo("title","テレビ")
                    .findAll()
            val tvadapter =
                    TaskAdapter(this, book, object: TaskAdapter.OnItemLongClickListener {
                        override fun onItemLongClick(item: Task) {
                            // クリック時の処理
                            Toast.makeText(applicationContext, item.content + "を削除しました", Toast.LENGTH_SHORT).show()
                            delete(item.id)
                        }
                    }, true)
            recyclerView.adapter=tvadapter
            Log.d(TAG,book.toString())
        }
        button3.setOnClickListener {
            val book = realm.where(Task::class.java)
                    .equalTo("title","フレーズ")
                    .findAll()
            val flaseadapter =
                    TaskAdapter(this, book, object: TaskAdapter.OnItemLongClickListener {
                        override fun onItemLongClick(item: Task) {
                            // クリック時の処理
                            Toast.makeText(applicationContext, item.content + "を削除しました", Toast.LENGTH_SHORT).show()
                            delete(item.id)
                        }
                    }, true)
            recyclerView.adapter=flaseadapter
            Log.d(TAG,book.toString())
        }
        button4.setOnClickListener {
            val book = realm.where(Task::class.java)
                    .equalTo("title","コント")
                    .findAll()
            val pictureadapter =
                    TaskAdapter(this, book, object: TaskAdapter.OnItemLongClickListener {
                        override fun onItemLongClick(item: Task) {
                            // クリック時の処理
                            Toast.makeText(applicationContext, item.content + "を削除しました", Toast.LENGTH_SHORT).show()
                            delete(item.id)
                        }
                    }, true)
            recyclerView.adapter=pictureadapter
            Log.d(TAG,book.toString())
        }
        button5.setOnClickListener {
            val book = realm.where(Task::class.java)
                    .equalTo("title","フリースタイル")
                    .findAll()
            val freeadapter =
                    TaskAdapter(this, book, object: TaskAdapter.OnItemLongClickListener {
                        override fun onItemLongClick(item: Task) {
                            // クリック時の処理
                            Toast.makeText(applicationContext, item.content + "を削除しました", Toast.LENGTH_SHORT).show()
                            delete(item.id)
                        }
                    }, true)
            recyclerView.adapter=freeadapter
            Log.d(TAG,book.toString())
        }

    }
    //オーバーフローメニューを押した時に画面が遷移する
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var returnVal = true
        val toSettingActivityIntent = Intent(this,TimePickerDialogActivity::class.java)
        val toSubuSettingIntent = Intent(this,SubuSetting::class.java)

        when(item.itemId){
            R.id.MenuSetting->
                startActivity(toSettingActivityIntent)

            R.id.SubuSetting->
                startActivity(toSubuSettingIntent)
        }
        return super.onOptionsItemSelected(item);
    }
    //アクションバーに追加
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options_menu_list,menu)
        return true
    }

    //以下はtaskリサイクルヴューの記述
    fun readAll(): RealmResults<Task> {
        return realm.where(Task::class.java).findAll().sort("createdAt", Sort.ASCENDING)
    }

    fun delete(id: String) {
        realm.executeTransaction {
            val task = realm.where(Task::class.java).equalTo("id", id).findFirst()
                ?: return@executeTransaction
            task.deleteFromRealm()
        }
    }
}




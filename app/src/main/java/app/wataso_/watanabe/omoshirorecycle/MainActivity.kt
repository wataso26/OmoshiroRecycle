package app.wataso_.watanabe.omoshirorecycle
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.res.Resources
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
        setTheme(R.style.Theme_OmoshiroRecycle)
        setContentView(R.layout.activity_main)

        //最初の使い方の案内
        val shr = getSharedPreferences("beginner", Context.MODE_PRIVATE)
        var Number1=shr.getInt("number",0)
        if (Number1==0){

            AlertDialog.Builder(this)
                    .setMessage("保存は、右下のボタンから出来ます！")
                    .setPositiveButton("やってみる", { dialog, which ->
                    })
                    .show()
            AlertDialog.Builder(this)
                    .setMessage("ジャンル分けの例は\n右上にあります")
                    .setPositiveButton("次へ", { dialog, which ->
                    })
                    .show()
            AlertDialog.Builder(this)
                    .setMessage("このアプリに、\n過去の面白かった事を保存していきましょう！")
                    .setPositiveButton("次へ", { dialog, which ->
                    })
                    .show()

        }
        //最初の説明の解除
        val editor=shr.edit()
        var Number2=shr.getInt("number",0)
        if(Number2==0){
            editor.putInt("number",1)
            editor.apply()
        }

        //以下12行はtaskのリサイクラビュー
        val taskList = readAll()

        val adapter =
            TaskAdapter(this, taskList, object : TaskAdapter.OnItemLongClickListener {
                override fun  onItemLongClick(item: Task) {
                    // クリック時の処理
                    AlertDialog.Builder(this@MainActivity)
                            .setMessage("削除しますか？")
                            .setPositiveButton("YES", { dialog, which ->
                                Toast.makeText(applicationContext, item.content + "を削除しました", Toast.LENGTH_SHORT).show()
                                delete(item.id)
                            })
                            .setNegativeButton("No",{dialog,which ->
                            })
                            .show()


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
                            AlertDialog.Builder(this@MainActivity)
                                    .setMessage("削除しますか？")
                                    .setPositiveButton("YES", { dialog, which ->
                                        Toast.makeText(applicationContext, item.content + "を削除しました", Toast.LENGTH_SHORT).show()
                                        delete(item.id)
                                    })
                                    .setNegativeButton("No",{dialog,which ->
                                    })
                                    .show()
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
                            AlertDialog.Builder(this@MainActivity)
                                    .setMessage("削除しますか？")
                                    .setPositiveButton("YES", { dialog, which ->
                                        Toast.makeText(applicationContext, item.content + "を削除しました", Toast.LENGTH_SHORT).show()
                                        delete(item.id)
                                    })
                                    .setNegativeButton("No",{dialog,which ->
                                    })
                                    .show()
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
                            AlertDialog.Builder(this@MainActivity)
                                    .setMessage("削除しますか？")
                                    .setPositiveButton("YES", { dialog, which ->
                                        Toast.makeText(applicationContext, item.content + "を削除しました", Toast.LENGTH_SHORT).show()
                                        delete(item.id)
                                    })
                                    .setNegativeButton("No",{dialog,which ->
                                    })
                                    .show()
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
                            AlertDialog.Builder(this@MainActivity)
                                    .setMessage("削除しますか？")
                                    .setPositiveButton("YES", { dialog, which ->
                                        Toast.makeText(applicationContext, item.content + "を削除しました", Toast.LENGTH_SHORT).show()
                                        delete(item.id)
                                    })
                                    .setNegativeButton("No",{dialog,which ->
                                    })
                                    .show()
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
                            AlertDialog.Builder(this@MainActivity)
                                    .setMessage("削除しますか？")
                                    .setPositiveButton("YES", { dialog, which ->
                                        Toast.makeText(applicationContext, item.content + "を削除しました", Toast.LENGTH_SHORT).show()
                                        delete(item.id)
                                    })
                                    .setNegativeButton("No",{dialog,which ->
                                    })
                                    .show()
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
                            AlertDialog.Builder(this@MainActivity)
                                    .setMessage("削除しますか？")
                                    .setPositiveButton("YES", { dialog, which ->
                                        Toast.makeText(applicationContext, item.content + "を削除しました", Toast.LENGTH_SHORT).show()
                                        delete(item.id)
                                    })
                                    .setNegativeButton("No",{dialog,which ->
                                    })
                                    .show()
                        }
                    }, true)
            recyclerView.adapter=freeadapter
            Log.d(TAG,book.toString())
        }
    }


    //オーバーフローメニューを押した時に画面が遷移する
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

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




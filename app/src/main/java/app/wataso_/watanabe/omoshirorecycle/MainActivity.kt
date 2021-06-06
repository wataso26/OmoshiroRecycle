package app.wataso_.watanabe.omoshirorecycle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import java.net.URI.create
import java.nio.file.Files.delete
import java.util.*


class MainActivity : AppCompatActivity() {
    //realmの定義
    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }
    //recyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //以下12行はtaskのリサイクラビュー
        val taskList = readAll()

        // タスクリストが空だったときにダミーデータを生成する
        if (taskList.isEmpty()) {
            createDummyData()
        }

        val adapter =
            TaskAdapter(this, taskList, object : TaskAdapter.OnItemClickListener {
                override fun onItemClick(item: Task) {
                    // クリック時の処理
                    Toast.makeText(applicationContext, item.content + "を削除しました", Toast.LENGTH_SHORT).show()

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



    }
    //オーバーフローメニューを押した時に画面が遷移する
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var returnVal = true
        val toSettingActivityIntent = Intent(this,TimePickerDialogActivity::class.java)

        when(item.itemId){
            R.id.MenuSetting->

                startActivity(toSettingActivityIntent)

        }
        return super.onOptionsItemSelected(item);
    }
    //アクションバーに追加
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options_menu_list,menu)
        return true
    }



    //以下はtaskリサイクルヴューの記述
    fun createDummyData() {
        for (i in 0..10) {
            create(R.drawable.ic_launcher_background, "思い出 $i")
        }
    }

    fun create(imageId: Int, content: String) {
        realm.executeTransaction {
            val task = it.createObject(Task::class.java, UUID.randomUUID().toString())
            task.imageId = imageId
            task.content = content
        }
    }

    fun readAll(): RealmResults<Task> {
        return realm.where(Task::class.java).findAll().sort("createdAt", Sort.ASCENDING)
    }

    //アイテムを削除する方法
    fun update(id: String, content: String) {
        realm.executeTransaction {
            val task = realm.where(Task::class.java).equalTo("id", id).findFirst()
                ?: return@executeTransaction
            task.content = content
        }
    }

    fun update(task: Task, content: String) {
        realm.executeTransaction {
            task.content = content
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


package app.wataso_.watanabe.omoshirorecycle
import android.content.Intent
import android.icu.text.CaseMap
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
import kotlinx.android.synthetic.main.toko.*
import java.net.URI.create
import java.nio.file.Files.delete
import java.util.*


class MainActivity : AppCompatActivity() {
    //realmの定義


    //realm
    val realm:Realm =Realm.getDefaultInstance()

    //recyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //以下12行はtaskのリサイクラビュー
        val taskList = readAll()


        val adapter =
            TaskAdapter(this, taskList, object : TaskAdapter.OnItemClickListener {
                override fun onItemClick(item: Task) {
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

}




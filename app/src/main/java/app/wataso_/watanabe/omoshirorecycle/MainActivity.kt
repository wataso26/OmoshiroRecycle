package app.wataso_.watanabe.omoshirorecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URI.create
import java.util.*

class MainActivity : AppCompatActivity() {
    //realmの定義
    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataList = readAll()

        // タスクリストが空だったときにダミーデータを生成する
        if (dataList.isEmpty()) {
            createDummyData()
        }

        val adapter = Datadapter(this, dataList, true)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        override fun onDestroy() {
            super.onDestroy()
            realm.close()
        }

        fun createDummyData() {
            for (i in 0..10) {
                create( "投稿 $i")
            }
        }

        fun create(imageId: Int, content: String) {
            realm.executeTransaction {
                val task = it.createObject(Data::class.java, UUID.randomUUID().toString())
                task.imageId = imageId
                task.content = content
            }
        }

        fun readAll(): RealmResults<Data> {
            return realm.where(Data::class.java).findAll().sort("createdAt", Sort.ASCENDING)
        }

        //投稿ボタンを押した時に画面が遷移する
        tokoButton.setOnClickListener {
            val toTokoActivityIntent = Intent(this,Toko::class.java)
            startActivity(toTokoActivityIntent)
        }

    }
}
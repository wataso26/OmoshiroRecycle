package app.wataso_.watanabe.omoshirorecycle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    //recyclerView
    private var page =1
    private var mainAdapter :MainAdapter? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.main_recycler_view)
        // RecyclerViewのレイアウトサイズを変更しない設定をONにする
        // パフォーマンス向上のための設定。
        recyclerView.setHasFixedSize(true)
        // RecyclerViewにlayoutManagerをセットする。
        // このlayoutManagerの種類によって「1列のリスト」なのか「２列のリスト」とかが選べる。
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        // Adapter生成してRecyclerViewにセット
        mainAdapter = MainAdapter(createRowData(page))
        recyclerView.adapter = mainAdapter


        //投稿ボタンを押した時に画面が遷移する
        tokoButton.setOnClickListener {
            val toTokoActivityIntent = Intent(this,Toko::class.java)
            startActivity(toTokoActivityIntent)
        }
        //設定ボタンを押した時に画面が遷移する
        settingButton.setOnClickListener {
            val toSettingActivityIntent = Intent(this,TimePickerDialogActivity::class.java)
            startActivity(toSettingActivityIntent)
        }


    }


    private fun createRowData(page: Int): List<RowData> {
        val dataSet: MutableList<RowData> = ArrayList()
        var i = 1
        while (i < page * 20) {
            val data = RowData()
            data.hogeTitle = "hogeTitle" + Integer.toString(i)
            data.hogeContents = "hogeContents" + Integer.toString(i)
            val add = dataSet.add(data)
            i += 1
        }
        return dataSet
    }
        //20行追加する


        //一行分のデータ

    inner class RowData {
        var hogeTitle: String? = null
        var hogeContents: String? = null
    }
    // アクションバーに追加
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options_menu_list,menu)
        return true
    }

}
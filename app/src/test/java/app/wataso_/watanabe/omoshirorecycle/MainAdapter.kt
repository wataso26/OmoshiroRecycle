package app.wataso_.watanabe.omoshirorecycle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_data_cell.view.*
import org.junit.runner.manipulation.Ordering
import java.text.SimpleDateFormat
import java.util.*

class DataAdapter internal constructor(private var rowDataList: List<RowData>) : RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_cell, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val rowData = rowDataList[position]
        holder.hogeTitle.text = rowData.hogeTitle
        holder.hogeContents.text = rowData.hogeContents
    }

    override fun getItemCount(): Int {
        return rowDataList.size
    }
}
class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var hogeTitle: TextView = itemView.findViewById(R.id.dateTextView)
        var hogeContents: TextView = itemView.findViewById(R.id.contentTextView)
}


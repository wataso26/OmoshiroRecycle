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

class DataAdapter(
    private val context: Ordering.Context,
    private var dataList: OrderedRealmCollection<Data>?,
    private val autoUpdate: Boolean
) :
    RealmRecyclerViewAdapter<Data, DataAdapter.TaskViewHolder>(dataList, autoUpdate) {

    override fun getItemCount(): Int = dataList?.size ?: 0

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val data: Data = dataList?.get(position) ?: return

        holder.genreTextView.text = data.content
        holder.contentTextView.text = data.content
        holder.dateTextView.text =
            SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPANESE).format(data.createdAt)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TaskViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_data_cell, viewGroup, false)
        return TaskViewHolder(v)
    }

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val genreTextView: TextView = view.genreTextView
        val contentTextView: TextView = view.contentTextView
        val dateTextView: TextView = view.dateTextView
    }
ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss
}
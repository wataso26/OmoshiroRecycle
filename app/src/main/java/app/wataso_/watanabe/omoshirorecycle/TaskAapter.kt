package app.wataso_.watanabe.omoshirorecycle

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_data_cell.view.*
import kotlinx.android.synthetic.main.toko.view.*
import java.util.*

class TaskAdapter(
    private val context: Context,
    private var taskList: OrderedRealmCollection<Task>?,
    private var listener: AdapterView.OnItemClickListener,//追加
    private val autoUpdate: Boolean
) :
    RealmRecyclerViewAdapter<Task, TaskAdapter.TaskViewHolder>(taskList, autoUpdate) {

    override fun getItemCount(): Int = taskList?.size ?: 0

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task: Task = taskList?.get(position) ?: return

        holder.container.setOnClickListener{//追加
            listener.onItemClick(task)
        }

        //holder.imageView.setImageResource(task.imageId)
        holder.contentTextView.text = task.content
        holder.dateTextView.text =
            SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPANESE).format(task.createdAt)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TaskViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_data_cell, viewGroup, false)
        return TaskViewHolder(v)
    }

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val imageView: ImageView = view.imageView
        val container : LinearLayout = view.container
        val contentTextView: TextView = view.contentTextView
        val dateTextView: TextView = view.dateTextView
    }
    //追加
    interface OnItemClickListener {
        fun onItemClick(item: Task)
    }

}
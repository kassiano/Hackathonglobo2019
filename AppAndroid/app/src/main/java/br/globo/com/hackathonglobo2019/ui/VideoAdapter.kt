package br.globo.com.hackathonglobo2019.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.globo.com.hackathonglobo2019.model.Video


class VideoAdapter: RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    private lateinit var items: List<Video>


    var onclick :(Video)->Unit = {}

    override fun getItemCount() = if (::items.isInitialized) items.size else 0

    var itemOpacity:Float = 1f

    fun setItems(newItems: List<Video>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        /*
        val video = Uri.parse("android.resource://${holder.context.packageName}/${item.videoFile}")

        val mediaController = MediaController(holder.context)
        mediaController.setAnchorView(holder.videoView)
        holder.videoView.setMediaController(mediaController)
        */
        holder.imgTumbnail.setImageResource(item.tumbnail)

        holder.tvDate.text = item.date
        holder.tvMatch.text = item.match
        holder.tvTime.text = item.time

        holder.imgTumbnail.setOnClickListener {
            onclick(item)
        }


        holder.imgTumbnail.alpha = itemOpacity
        holder.tvDate.alpha = itemOpacity
        holder.tvMatch.alpha = itemOpacity
        holder.tvTime.alpha = itemOpacity

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(br.globo.com.hackathonglobo2019.R.layout.item_video, parent, false)
        return ViewHolder(view)
    }

    fun setOpacity(n: Float) {
        itemOpacity = n
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val context = itemView.context
        val imgTumbnail: ImageView = itemView.findViewById(br.globo.com.hackathonglobo2019.R.id.imgTumbnail)
        val tvDate: TextView = itemView.findViewById(br.globo.com.hackathonglobo2019.R.id.tvDate)
        val tvMatch: TextView = itemView.findViewById(br.globo.com.hackathonglobo2019.R.id.tvMatch)
        val tvTime: TextView = itemView.findViewById(br.globo.com.hackathonglobo2019.R.id.tvTime)
    }
}
package br.globo.com.hackathonglobo2019.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.globo.com.hackathonglobo2019.R
import br.globo.com.hackathonglobo2019.model.News

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private lateinit var items: List<News>

    override fun getItemCount() = if (::items.isInitialized) items.size else 0

    fun setItems(newItems: List<News>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvTitle.text = item.title
        holder.tvMoment.text = item.moment.toUpperCase()
        holder.tvDescription.text = item.description
        holder.imgNew.setImageResource(item.image)
        holder.tvWatching.text = item.watching
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvMoment: TextView = itemView.findViewById(R.id.tvMoment)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val imgNew: ImageView = itemView.findViewById(R.id.imgNew)
        val tvWatching: TextView = itemView.findViewById(R.id.tvWatching)
    }
}
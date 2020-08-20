package com.example.assignmentweek3.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignmentweek3.DetailActivity
import com.example.assignmentweek3.R
import com.example.assignmentweek3.model.News
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(var data : ArrayList<News>?) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.imgNews
        val title = itemView.tvTitleNews
        val author = itemView.tvAuthor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.title.text = data?.get(position)?.title
        holder.author.text = data?.get(position)?.author

        Glide.with(holder.itemView.context)
            .load(data?.get(position)?.urlToImage)
            .into(holder.img)

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("title", data?.get(position)?.title)
            intent.putExtra("published", data?.get(position)?.publishedAt)
            intent.putExtra("img", data?.get(position)?.urlToImage)
            intent.putExtra("desc", data?.get(position)?.description)
            intent.putExtra("author", data?.get(position)?.author)

            holder.itemView.context.startActivity(intent)
        }
    }

}
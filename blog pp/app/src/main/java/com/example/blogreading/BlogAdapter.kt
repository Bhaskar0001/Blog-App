package com.example.blogreading

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BlogAdapter(private val blogList: List<BlogPost>, private val onClick: (BlogPost) -> Unit) :
    RecyclerView.Adapter<BlogAdapter.BlogViewHolder>() {

    inner class BlogViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.blogTitle)
        val excerptTextView: TextView = view.findViewById(R.id.blogExcerpt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_blog, parent, false)
        return BlogViewHolder(view)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val blogPost = blogList[position]
        holder.titleTextView.text = blogPost.title.rendered
        holder.excerptTextView.text = android.text.Html.fromHtml(blogPost.excerpt.rendered)
        holder.itemView.setOnClickListener { onClick(blogPost) }
    }

    override fun getItemCount(): Int = blogList.size
}

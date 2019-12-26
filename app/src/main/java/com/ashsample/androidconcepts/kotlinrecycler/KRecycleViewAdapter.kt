package com.ashsample.androidconcepts.kotlinrecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashsample.androidconcepts.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.k_recycleview_item.view.*

class KRecycleViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<RecycleViewItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return KRecycleViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.k_recycleview_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is KRecycleViewHolder -> {
                holder.bind(items.get(position))
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(blogList: List<RecycleViewItem>){
        items = blogList
    }



    class KRecycleViewHolder
    constructor(
            itemView: View
    ): RecyclerView.ViewHolder(itemView){

        val blog_image = itemView.blog_image
        val blog_title = itemView.blog_title
        val blog_author = itemView.blog_author

        fun bind(blogPost: RecycleViewItem){

            val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(blogPost.image)
                    .into(blog_image)
            blog_title.setText(blogPost.title)
            blog_author.setText(blogPost.username)

        }

    }
}
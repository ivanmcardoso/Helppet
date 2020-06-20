package com.hefesto.helppet.adapter

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.helppet.R
import com.hefesto.helppet.model.Post
import kotlinx.android.synthetic.main.list_item_post.view.*
import java.util.*

class PostAdapter (private val postList: List<Post>,
                    private val onItemClick: (Post) -> Unit)
    : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView (post: Post, onClickListener: (Post) -> Unit) {
            val name = itemView.tvName
            val time = itemView.tvTimeAgo
            val description = itemView.tvDescription

            name.text = post.name
            time.text = DateUtils.getRelativeTimeSpanString(post.time.time, Calendar.getInstance().timeInMillis, DateUtils.MINUTE_IN_MILLIS)
            description.text = post.description

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val postLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item_post, parent, false)
        return PostViewHolder(postLayout)
    }

    override fun getItemCount() = postList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder?.let {
            it.bindView(postList[position]) { post -> onItemClick(post) }
        }
    }
}
package com.hefesto.helppet.adapter

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hefesto.helppet.R
import com.hefesto.helppet.model.Posts
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_post.view.*
import java.util.*

class PostAdapter (private val postList: List<Posts>,
                   private val onItemClick: (Posts) -> Unit)
    : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView (post: Posts, onClickListener: (Posts) -> Unit) {
            val name = itemView.tvName
            val time = itemView.tvTimeAgo
            val description = itemView.tvDescription

            name.text = post.name
            time.text = DateUtils.getRelativeTimeSpanString(post.time!!.time, Calendar.getInstance().timeInMillis, DateUtils.MINUTE_IN_MILLIS)
            description.text = post.description
            Picasso.get().load(post.petPhotoUrl).into(itemView.ivPetPhoto)
            Picasso.get().load(post.userphotoUrl).into(itemView.ivProfilePicture)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val postLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item_post, parent, false)
        return PostViewHolder(postLayout)
    }

    override fun getItemCount() = postList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.let {
            it.bindView(postList[position]) { post -> onItemClick(post) }
        }
    }
}
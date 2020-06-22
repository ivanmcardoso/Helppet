package com.hefesto.helppet.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.hefesto.helppet.activity.PostActivity
import com.hefesto.helppet.R
import com.hefesto.helppet.adapter.PostAdapter
import com.hefesto.helppet.config.FirebaseConfig
import com.hefesto.helppet.model.Posts
import com.hefesto.helppet.utils.shortToast
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment: Fragment() {
    private var postList: MutableList<Posts> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_feed, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecycleView()
        fabNewPost.setOnClickListener { Intent(context, PostActivity::class.java).also { startActivity(it) } }
    }

    private fun setupRecycleView() {
        pbFeed.visibility = View.VISIBLE
        FirebaseConfig.getDatabase().child("Post").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                pbFeed.visibility = View.GONE
                shortToast("falha:"+ error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val list : MutableList<Posts> = mutableListOf()
                val children = snapshot!!.children
                children?.forEach {
                    it.getValue(Posts::class.java)?.let { it1 -> list.add(it1) }
                }
                pbFeed.visibility = View.GONE
                appendPostList(list)
            }

        })
        }

    private fun appendPostList(list: MutableList<Posts>) {
        postList = list
        rvPosts.adapter = PostAdapter(postList){}
    }
}
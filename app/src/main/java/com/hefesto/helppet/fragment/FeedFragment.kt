package com.hefesto.helppet.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hefesto.helppet.R
import com.hefesto.helppet.adapter.PostAdapter
import com.hefesto.helppet.model.Post
import kotlinx.android.synthetic.main.fragment_feed.*
import java.util.*

class FeedFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_feed, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val postList = fillPostList()
        rvPosts.adapter = PostAdapter(postList){}
    }

    private fun fillPostList() = mutableListOf(
        Post("Susan Su",
                Date(),
            "Vi este dog, na esquina da Rua 3, eu alimentei e esta com uma pata ferida.",
            "abandoned"
        )
    )
}
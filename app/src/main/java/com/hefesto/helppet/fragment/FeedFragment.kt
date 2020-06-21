package com.hefesto.helppet.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.model.LatLng
import com.hefesto.helppet.PostActivity
import com.hefesto.helppet.R
import com.hefesto.helppet.adapter.PostAdapter
import com.hefesto.helppet.model.Post
import kotlinx.android.synthetic.main.fragment_feed.*
import java.io.File
import java.util.*

class FeedFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_feed, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val postList = fillPostList()
        rvPosts.adapter = PostAdapter(postList){}

        fabNewPost.setOnClickListener { Intent(context, PostActivity::class.java).also { startActivity(it) } }
    }

    private fun fillPostList() = mutableListOf(
        Post("Susan Su",
                Date(),
            Uri.parse("https://upload.wikimedia.org/wikipedia/commons/thumb/4/4d/Stray_dogs_crosswalk.jpg/1024px-Stray_dogs_crosswalk.jpg"),
            Uri.parse("https://i.pinimg.com/236x/77/a8/d5/77a8d552e2b48c1876cede11a7d89c95.jpg"),
            "Vi este dog, na esquina da Rua 3, eu alimentei e esta com uma pata ferida.",
            LatLng(-3.091583, -60.0198654)
        )
    )
}
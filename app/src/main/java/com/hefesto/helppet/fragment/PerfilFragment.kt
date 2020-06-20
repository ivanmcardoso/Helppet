package com.hefesto.helppet.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hefesto.helppet.R
import com.hefesto.helppet.config.FirebaseConfig
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_perfil.*

class PerfilFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_perfil, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        FirebaseConfig.getCurrentUser()?.let {
            tvProfileName.text = it.displayName
            tvProfileDescription.text = "Hi! My name is ${it.displayName} and i love pets."
            var photoUrl = it.photoUrl
            Picasso.get().load(photoUrl).into(ivProfileProfilePicture)
        }
    }
}
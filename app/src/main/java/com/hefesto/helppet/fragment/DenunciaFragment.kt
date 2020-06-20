package com.hefesto.helppet.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hefesto.helppet.R
import com.hefesto.helppet.activity.OngsActivity
import kotlinx.android.synthetic.main.fragment_denuncia.*

class DenunciaFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_denuncia, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btDenunciaOngs.setOnClickListener { Intent(context, OngsActivity::class.java).also { startActivity(it) } }
    }
}
package com.hefesto.helppet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView

class denuncia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_denuncia)
        val text = findViewById(R.id.link) as TextView
        text.setMovementMethod(LinkMovementMethod.getInstance())
    }
}
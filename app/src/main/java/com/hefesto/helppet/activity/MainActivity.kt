package com.hefesto.helppet.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hefesto.helppet.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btSavior.setOnClickListener { startActivity(Intent(this, HomeActivity::class.java)) }
        btAdopter.setOnClickListener { startActivity(Intent(this, HomeActivity::class.java)) }
    }
}
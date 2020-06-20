package com.hefesto.helppet.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hefesto.helppet.R
import com.hefesto.helppet.fragment.LoginFragment
import com.hefesto.helppet.fragment.UserFormFragment

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportFragmentManager.beginTransaction().replace(R.id.flLogin, LoginFragment()).commit()
    }
}
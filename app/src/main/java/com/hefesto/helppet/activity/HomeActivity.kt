package com.hefesto.helppet.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hefesto.helppet.R
import com.hefesto.helppet.fragment.DenunciaFragment
import com.hefesto.helppet.fragment.FeedFragment
import com.hefesto.helppet.fragment.PerfilFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupNavigationMenu()
        bnMainMenu.selectedItemId = R.id.nav_feed
    }

    private fun setupNavigationMenu() {
        bnMainMenu.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.nav_feed -> {
                    supportFragmentManager.beginTransaction().replace(R.id.flMain, FeedFragment())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.flMain, PerfilFragment())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_info -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flMain, DenunciaFragment()).commit()
                    return@setOnNavigationItemSelectedListener true
                }
            }

            false
        }
    }
}
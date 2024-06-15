package com.bagibagi.app.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bagibagi.app.R
import com.bagibagi.app.databinding.ActivityMainBinding
import com.bagibagi.app.ui.fragment.HistoryFragment
import com.bagibagi.app.ui.fragment.HomeFragment
import com.bagibagi.app.ui.fragment.NotificationsFragment
import com.bagibagi.app.ui.additem.AddItemActivity
import com.bagibagi.app.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity()  {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.background = null
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> openFragment(HomeFragment())
                R.id.nav_notifications -> openFragment(NotificationsFragment())
                R.id.nav_history -> openFragment(HistoryFragment())
                R.id.nav_profile -> openFragment(ProfileFragment())
            }
            true
        }
        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())

        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this@MainActivity,AddItemActivity::class.java))
        }
    }
    private fun openFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}

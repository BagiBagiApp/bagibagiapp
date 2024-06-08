package com.bagibagi.app.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bagibagi.app.R
import com.bagibagi.app.databinding.ActivityMainBinding
//import com.bagibagi.app.ui.notifications.NotificationsActivity
//import com.bagibagi.app.ui.add.AddActivity
//import com.bagibagi.app.ui.history.HistoryActivity
import com.bagibagi.app.ui.profile.ProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigation: BottomNavigationView = binding.bottomNavigation

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> if (!isCurrentActivity(MainActivity::class.java)) {
                    startActivity(Intent(this, MainActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                }
//                R.id.nav_notifications -> if (!isCurrentActivity(NotificationsActivity::class.java)) {
//                    startActivity(Intent(this, NotificationsActivity::class.java))
//                    overridePendingTransition(0, 0)
//                    finish()
//                }
//                R.id.nav_add -> if (!isCurrentActivity(AddActivity::class.java)) {
//                    startActivity(Intent(this, AddActivity::class.java))
//                    overridePendingTransition(0, 0)
//                    finish()
//                }
//                R.id.nav_history -> if (!isCurrentActivity(HistoryActivity::class.java)) {
//                    startActivity(Intent(this, HistoryActivity::class.java))
//                    overridePendingTransition(0, 0)
//                    finish()
//                }
                R.id.nav_profile -> if (!isCurrentActivity(ProfileActivity::class.java)) {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                }
            }
            true
        }

        // Set default selection
        bottomNavigation.selectedItemId = R.id.nav_home
    }

    private fun isCurrentActivity(activityClass: Class<out AppCompatActivity>): Boolean {
        return this::class.java == activityClass
    }
}

package com.bagibagi.app.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bagibagi.app.R
import com.bagibagi.app.databinding.ActivityWelcomeBinding
import com.bagibagi.app.ui.login.LoginActivity
import com.bagibagi.app.ui.signup.SignupActivity

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnLogin.setOnClickListener { startActivity(Intent(this@WelcomeActivity, LoginActivity::class.java)) }
            btnSignup.setOnClickListener { startActivity(Intent(this@WelcomeActivity, SignupActivity::class.java)) }
        }
    }
}
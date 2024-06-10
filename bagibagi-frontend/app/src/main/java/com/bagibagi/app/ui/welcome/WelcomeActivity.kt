package com.bagibagi.app.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bagibagi.app.R
import com.bagibagi.app.databinding.ActivityWelcomeBinding
import com.bagibagi.app.ui.ViewModelFactory
import com.bagibagi.app.ui.login.LoginActivity
import com.bagibagi.app.ui.main.MainActivity
import com.bagibagi.app.ui.profile.ProfileActivity
import com.bagibagi.app.ui.signup.SignupActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityWelcomeBinding

    private val viewModel by viewModels<WelcomeViewModel>(){
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUILogic()
    }
    private fun setUILogic(){
        startActivity(Intent(this,MainActivity::class.java))

        /*
        // TODO: ProfileActivity -> MainActivity
        viewModel.getSession().observe(this){ user ->
        if(user.isLogin){
        startActivity(Intent(this, ProfileActivity::class.java))
        finish()
        }
        }
        */

        with(binding){
            btnLogin.setOnClickListener { startActivity(Intent(this@WelcomeActivity, LoginActivity::class.java)) }
            btnSignup.setOnClickListener { startActivity(Intent(this@WelcomeActivity, SignupActivity::class.java)) }
        }
    }
}
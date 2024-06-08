package com.bagibagi.app.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bagibagi.app.R
import com.bagibagi.app.databinding.ActivityLoginBinding
import com.bagibagi.app.ui.signup.SignupActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUILogic()
    }
    private fun setUILogic(){
        binding.progressIndicator.visibility = View.INVISIBLE

        binding.txtSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }
    }
}
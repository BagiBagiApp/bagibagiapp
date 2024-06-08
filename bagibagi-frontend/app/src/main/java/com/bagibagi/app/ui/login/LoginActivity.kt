package com.bagibagi.app.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bagibagi.app.R
import com.bagibagi.app.databinding.ActivityLoginBinding
import com.bagibagi.app.ui.ViewModelFactory
import com.bagibagi.app.ui.signup.SignupActivity
import com.bagibagi.app.ui.welcome.WelcomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    private val viewModel by viewModels<LoginViewModel>(){
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUILogic()
    }
    private fun setUILogic(){
        binding.progressIndicator.visibility = View.INVISIBLE

        with(binding){
            btnLogin.setOnClickListener {
                val username = txtUsername.text.toString()
                val password = txtPasswordLogin.text.toString()

                binding.progressIndicator.visibility = View.VISIBLE

                lifecycleScope.launch {
                    try {
                        viewModel.login(username,password).apply {
                            if (token != null) {
                                Snackbar.make(it, "Login Success", Snackbar.LENGTH_SHORT).show()
                                progressIndicator.visibility = View.INVISIBLE
                            } else if (message != null) {
                                Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
                                progressIndicator.visibility = View.INVISIBLE
                            }
                        }
                    }
                    catch (e:HttpException){
                        Log.e("LOGIN_ACTIVITY", "HTTP Exception: ${e.code()} ${e.message()}")
                        progressIndicator.visibility = View.INVISIBLE
                    }
                }
            }
        }
        binding.txtSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }
    }
}
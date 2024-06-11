package com.bagibagi.app.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bagibagi.app.R
import com.bagibagi.app.databinding.ActivityProfileBinding
import com.bagibagi.app.ui.ViewModelFactory
import com.bagibagi.app.ui.login.LoginViewModel
import com.bagibagi.app.ui.welcome.WelcomeActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfileBinding

    private val viewModel by viewModels<ProfileViewModel>(){
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUILogic()
        getUserData()
    }
    private fun setUILogic(){
        with(binding){
            btnLogoutProfile.setOnClickListener {
                lifecycleScope.launch{
                    viewModel.logout()
                    startActivity(Intent(this@ProfileActivity,WelcomeActivity::class.java))
                    finish()
                }
            }
        }
    }
    private fun getUserData(){
        viewModel.getUserDetail()

        viewModel.userDetail.observe(this, Observer { userDetail ->
            userDetail.forEach {
                with(binding){
                    txtUsernameProfile.setText(it.username)
                    txtAddressProfile.setText(it.alamat)
                    txtWhatsappProfile.setText("wa.me/${it.notelp}")
                }
            }
        })

        viewModel.error.observe(this, Observer { errorMessage ->
            Log.e("PROFILE_ACTIVITY",errorMessage)
            Snackbar.make(binding.root,"Error: $errorMessage",Toast.LENGTH_SHORT).show()
        })
    }
}
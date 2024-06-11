package com.bagibagi.app.ui.splashscreen

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.lifecycleScope
import com.bagibagi.app.databinding.ActivitySplashscreenBinding
import com.bagibagi.app.ui.welcome.WelcomeActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashscreenBinding
    private val splashScreenTimeout: Long = 1600

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()

        lifecycleScope.launch {
            delay(splashScreenTimeout)
            withContext(Dispatchers.Main) {
                val intent = Intent(this@SplashScreenActivity, WelcomeActivity::class.java)
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this@SplashScreenActivity).toBundle())
                finish()
            }
        }
    }

    private fun playAnimation(){
        val logo = ObjectAnimator.ofFloat(binding.ivLogo, View.ALPHA, 1f).setDuration(800)
        val logoBrand =  ObjectAnimator.ofFloat(binding.ivLogoBranding, View.ALPHA, 1f).setDuration(800)

        AnimatorSet().apply {
            playSequentially(logo, logoBrand)
            start()
        }
    }
}
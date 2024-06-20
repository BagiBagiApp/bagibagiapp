package com.bagibagi.app.ui.itemdetailuser

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bagibagi.app.R
import com.bagibagi.app.databinding.ActivityUserItemDetailBinding
import com.bagibagi.app.ui.profile.UserProductAdapter

class UserItemDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUserItemDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO : SELESAIIN USER ITEM DETAIL
        val itemDetail = intent.getBundleExtra(UserProductAdapter.EXTRA_ITEM)
        Log.d("UserItemDetailActivity", "item: $itemDetail" )
    }
}
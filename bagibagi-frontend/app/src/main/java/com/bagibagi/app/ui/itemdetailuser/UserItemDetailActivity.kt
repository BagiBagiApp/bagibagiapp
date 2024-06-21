package com.bagibagi.app.ui.itemdetailuser

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bagibagi.app.R
import com.bagibagi.app.data.model.ItemModel
import com.bagibagi.app.data.model.UserItemModel
import com.bagibagi.app.databinding.ActivityUserItemDetailBinding
import com.bagibagi.app.helper.apiToUILanguage
import com.bagibagi.app.helper.showSnackbar
import com.bagibagi.app.ui.ViewModelFactory
import com.bagibagi.app.ui.login.LoginViewModel
import com.bagibagi.app.ui.profile.UserProductAdapter
import com.bumptech.glide.Glide

class UserItemDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUserItemDetailBinding

    private val viewModel by viewModels<UserItemDetailViewModel>(){
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemID = intent.getIntExtra(UserProductAdapter.ITEM_ID,-1)

        setUI(itemID)

        // TODO : Edit & Delete Feature
        binding.btnEditUserDetailItem.setOnClickListener {  }
        binding.btnDeleteUserDetailItem.setOnClickListener {  }
    }
    private fun setUI(itemID : Int){
        viewModel.getItemDetail(itemID)

        viewModel.itemDetail.observe(this,){ item ->
            with(binding){
                Glide.with(this@UserItemDetailActivity)
                    .load(item.link_foto)
                    .fitCenter()
                    .into(ivUserDetailItem)
                chipCategoryUserItemDetail.setText(apiToUILanguage(item.kategori))
                tvNamaBarangItemDetail.text = item.nama_produk
                tvQuantityUserDetailItem.text = item.qty.toString()
                tvYearsOfUsageUserDetailItem.text = item.years_of_usage
                tvDescUserItemDetail.text = item.desc
                tvAddressUserItemDetail.text = item.alamat
            }
        }
    }
}
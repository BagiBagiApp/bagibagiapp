package com.bagibagi.app.ui.itemdetail

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bagibagi.app.R
import com.bagibagi.app.data.model.ItemDetailModel
import com.bagibagi.app.data.model.UserItemModel
import com.bagibagi.app.databinding.ActivityItemDetailBinding
import com.bagibagi.app.helper.apiToUILanguage
import com.bagibagi.app.helper.showSnackbar
import com.bagibagi.app.ui.ViewModelFactory
import com.bagibagi.app.ui.home.ItemAdapter
import com.bagibagi.app.ui.itemdetailuser.UserItemDetailViewModel
import com.bagibagi.app.ui.profile.UserProductAdapter
import com.bumptech.glide.Glide

class ItemDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityItemDetailBinding

    private val viewModel by viewModels<ItemDetailViewModel>(){
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemID = intent.getIntExtra(ItemAdapter.EXTRA_ITEM_ID,-1)

        setUI(itemID)

        binding.btnRequestItemDetail.setOnClickListener {  }
    }
    private fun setUI(itemID : Int){

        viewModel.getItemDetail(itemID)

        viewModel.itemDetail.observe(this){ item ->
            with(binding){
                Glide.with(this@ItemDetailActivity)
                    .load(item.link_foto)
                    .fitCenter()
                    .into(ivDetailItem)
                chipCategoryItemDetail.setText(apiToUILanguage(item.kategori))
                tvNamaBarangItemDetail.text = item.nama_produk
                tvQuantityDetailItem.text = item.qty.toString()
                tvYearsOfUsageDetailItem.text = item.years_of_usage
                tvDescItemDetail.text = item.desc
                tvAddressItemDetail.text = item.alamat
            }
        }
    }
    private fun requestBarter(){

    }
}
package com.bagibagi.app.ui.barter

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bagibagi.app.R
import com.bagibagi.app.databinding.ActivityConfirmBinding
import com.bagibagi.app.helper.apiToUILanguage
import com.bagibagi.app.helper.showSnackbar
import com.bagibagi.app.ui.ViewModelFactory
import com.bumptech.glide.Glide

class ConfirmActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfirmBinding

    private val viewModel by viewModels<ConfirmActivityViewModel>(){
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idUserItem = intent.getIntExtra(ChooseItemAdapter.EXTRA_USERITEM_ID, -1)
        val idRecipientItem = intent.getIntExtra(ChooseItemAdapter.EXTRA_RECIPIENTITEM_ID, -1)
        setUI(idUserItem, idRecipientItem)

        binding.selectButton.setOnClickListener { requestBarter(idUserItem.toString(),idRecipientItem.toString()) }
    }
    private fun setUI(idUserItem : Int, idRecipientItem : Int){
        viewModel.getUserItem(idUserItem)
        viewModel.getRecipientItem(idRecipientItem)

        with(binding){
            viewModel.userItem.observe(this@ConfirmActivity,){
                Glide.with(binding.root)
                    .load(it.link_foto)
                    .fitCenter()
                    .into(ivItemConfirm1)
                tvCategoryConfirm1.text = apiToUILanguage(it.kategori)
                tvNamaItemConfirm1.text = it.nama_produk
                tvQtyItemConfirm1.text ="Qty tersisa: ${ it.qty.toString() }"
                tvYearsConfirm1.text = "Years of Usage: ${it.years_of_usage}"
                tvAlamatConfirm1.text = it.alamat
                tvDescConfirm1.text = it.desc
            }
            viewModel.recipientItem.observe(this@ConfirmActivity,){
                Glide.with(binding.root)
                    .load(it.link_foto)
                    .fitCenter()
                    .into(ivItemConfirm2)
                tvCategoryConfirm2.text = apiToUILanguage(it.kategori)
                tvNamaItemConfirm2.text = it.nama_produk
                tvQtyItemConfirm2.text ="Qty tersisa: ${ it.qty.toString() }"
                tvYearsConfirm2.text = "Years of Usage: ${it.years_of_usage}"
                tvAlamatConfirm2.text = it.alamat
                tvDescConfirm2.text = it.desc
            }
        }
        viewModel.error.observe(this,){
            showSnackbar(binding.root, it)
        }
    }
    private fun requestBarter(
        barangRequesterID : String,
        barangRecipientID : String,
    ){
        viewModel.requestBarter(barangRequesterID, barangRecipientID)

        viewModel.response.observe(this,){
            if (it == "Request Exchange Successful."){
                finish()
            }else{
                showSnackbar(binding.root,"$it")
            }
        }
    }
}
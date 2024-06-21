package com.bagibagi.app.ui.barter

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bagibagi.app.databinding.ActivityChooseBinding
import com.bagibagi.app.ui.ViewModelFactory
import com.bagibagi.app.ui.itemdetail.ItemDetailActivity
import com.google.android.material.snackbar.Snackbar

class ChooseItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseBinding

    private val viewModel by viewModels<ChooseItemViewModel>(){
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemRequestedID = intent.getIntExtra(ItemDetailActivity.EXTRA_ITEM_ID,-1)

        setUI(itemRequestedID, this)
    }
    private fun setUI(itemRequestedID:, context: Context){
        viewModel.getUserItem()

        viewModel.userItem.observe(this){
            binding.rvChoose.apply {
                layoutManager = GridLayoutManager(this@ChooseItemActivity, 2)
                setHasFixedSize(true)
                val userItemAdapter = ChooseItemAdapter(itemRequestedID, this@ChooseItemActivity)
                userItemAdapter.submitList(it)
                adapter = userItemAdapter
            }
        }
        viewModel.error.observe(this) {
            Log.e("ProfileFragment", "Error: $it")
            Snackbar.make(binding.root, "Error: $it", Snackbar.LENGTH_LONG).show()
        }
    }
}
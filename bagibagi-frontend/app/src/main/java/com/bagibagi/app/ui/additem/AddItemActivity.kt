package com.bagibagi.app.ui.additem

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.IntegerRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bagibagi.app.R
import com.bagibagi.app.data.api.ApiConfig
import com.bagibagi.app.data.response.UploadItemResponse
import com.bagibagi.app.databinding.ActivityAddItemBinding
import com.bagibagi.app.helper.reduceFileImage
import com.bagibagi.app.helper.showSnackbar
import com.bagibagi.app.helper.uriToFile
import com.bagibagi.app.ui.ViewModelFactory
import com.bagibagi.app.ui.login.LoginViewModel
import com.bagibagi.app.ui.media.MediaActivity
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException

class AddItemActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddItemBinding

    private var currentImageUri: Uri? = null

    private val viewModel by viewModels<AddItemViewModel>(){
        ViewModelFactory.getInstance(this)
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUILogic()
    }

    private fun setUILogic(){


            showLoading(false)

            val category = resources.getStringArray(R.array.category)
            val yearsOfUsage = resources.getStringArray(R.array.years_of_usage)
            val arrayAdapterCategory = ArrayAdapter(this,R.layout.dropdown_item,category)
            val arrayAdapterYearsOfUsage = ArrayAdapter(this,R.layout.dropdown_item,yearsOfUsage)
            binding.txtCategoryAddItem.setAdapter(arrayAdapterCategory)
            binding.txtYearsOfUsageAddItem.setAdapter(arrayAdapterYearsOfUsage)


            binding.btnCancel.setOnClickListener { finish() }
            binding.btnEditPhotoAddItem.setOnClickListener { startGallery() }
            binding.btnAddItem.setOnClickListener { uploadImage() }

    }
    private fun uploadImage(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val namaProduk = binding.txtNamaProduk.text.toString()
            val desc = binding.txtDescriptionAddItem.text.toString()
            val kategori = binding.txtCategoryAddItem.text.toString()
            val qty = binding.txtQty.text.toString().trim().toInt()
            val yearsOfUsageInput = binding.txtYearsOfUsageAddItem.text.toString()

            currentImageUri?.let {
                viewModel.uploadItem(
                    this,
                    it,
                    namaProduk,
                    desc,
                    kategori,
                    qty,
                    yearsOfUsageInput
                )
            }
            viewModel.showLoading.observe(this,) { showLoading(it) }
            viewModel.uploadResult.observe(this) { showSnackbar(binding.root, it) }
            viewModel.uploadErrorMessage.observe(this) { showSnackbar(binding.root, it) }
        }
    }
    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.ivPhotoAddItem.setImageURI(it)
        }
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
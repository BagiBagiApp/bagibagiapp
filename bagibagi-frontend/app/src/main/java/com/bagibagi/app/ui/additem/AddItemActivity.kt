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
        val category = resources.getStringArray(R.array.category)
        val yearsOfUsage = resources.getStringArray(R.array.years_of_usage)
        val arrayAdapterCategory = ArrayAdapter(this,R.layout.dropdown_item,category)
        val arrayAdapterYearsOfUsage = ArrayAdapter(this,R.layout.dropdown_item,yearsOfUsage)
        binding.txtCategoryAddItem.setAdapter(arrayAdapterCategory)
        binding.txtYearsOfUsageAddItem.setAdapter(arrayAdapterYearsOfUsage)

        binding.btnCancel.setOnClickListener { finish() }
        binding.btnEditPhotoAddItem.setOnClickListener { startGallery() }
        binding.btnAddItem.setOnClickListener {  }
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
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun uploadImage(
        namaProduk : String,
        desc : String,
        kategori : String,
        qty : Int,
        yearsOfUsage : String,

    ) {
        currentImageUri?.let { uri ->
            val imageFile = uriToFile(uri, this).reduceFileImage()
            Log.d("Image File", "showImage: ${imageFile.path}")
            val description = "Ini adalah deksripsi gambar"

            showLoading(true)

            val requestBody = description.toRequestBody("text/plain".toMediaType())
            val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData(
                "photo",
                imageFile.name,
                requestImageFile
            )
            lifecycleScope.launch {
                try {
                    val apiService = ApiConfig.getApiService()
                    //val successResponse = apiService.uploadItem(multipartBody, requestBody)
                    //showSnackbar(binding.root,)
                    showLoading(false)
                } catch (e: HttpException) {
                    val errorBody = e.response()?.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorBody, UploadItemResponse::class.java)
//                    showSnackbar(binding.root,errorResponse.)
                    showLoading(false)
                }
            }
        } ?: showSnackbar(binding.root, "Silakan pilih gambar terlebih dahulu")
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
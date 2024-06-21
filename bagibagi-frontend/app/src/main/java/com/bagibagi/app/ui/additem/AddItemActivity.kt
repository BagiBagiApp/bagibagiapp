package com.bagibagi.app.ui.additem

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import com.bagibagi.app.ImageClassifierHelper
import com.bagibagi.app.R
import com.bagibagi.app.data.api.ApiConfig
import com.bagibagi.app.data.response.UploadItemResponse
import com.bagibagi.app.databinding.ActivityAddItemBinding
import com.bagibagi.app.helper.reduceFileImage
import com.bagibagi.app.helper.showSnackbar
import com.bagibagi.app.helper.uriToFile
import com.bagibagi.app.ui.ViewModelFactory
import com.bagibagi.app.ui.login.LoginViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.tensorflow.lite.task.vision.classifier.Classifications
import retrofit2.HttpException
import java.text.NumberFormat

class AddItemActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddItemBinding

    private var currentImageUri: Uri? = null
    private var analyzedResult : String = ""

    private lateinit var imageClassifierHelper: ImageClassifierHelper
    private lateinit var progressDialog: ProgressDialog

    private val viewModel by viewModels<AddItemViewModel>(){
        ViewModelFactory.getInstance(this)
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        currentImageUri = uri
        showImage()
        analyzeImage()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUILogic()

        progressDialog = ProgressDialog(this).apply {
            setMessage("Analyzing image...")
            setCancelable(false)
        }
    }
    override fun onResume() {
        super.onResume()
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
            if(currentImageUri != null){
                val namaProduk = binding.txtNamaProduk.text.toString()
                val desc = binding.txtDescriptionAddItem.text.toString()
                val kategori = binding.txtCategoryAddItem.text.toString()
                val kategoriToSend = convertToApiString(kategori)
                val qty = binding.txtQty.text.toString().trim().toInt()
                val yearsOfUsageInput = binding.txtYearsOfUsageAddItem.text.toString()
                currentImageUri?.let {
                    viewModel.uploadItem(
                        this,
                        it,
                        namaProduk,
                        desc,
                        kategoriToSend,
                        qty,
                        yearsOfUsageInput
                    )
                }
                viewModel.showLoading.observe(this,) { showLoading(it) }
                viewModel.uploadResult.observe(this) { if(it != "") {showSnackbar(binding.root, it)} }
                viewModel.uploadErrorMessage.observe(this) { if(it != "") {showSnackbar(binding.root, it)} }
            }else{
                showSnackbar(binding.root, "Silakan masukkan berkas gambar terlebih dahulu.")
            }
        }
    }
    private fun analyzeImage() {
        if(currentImageUri != null){
            progressDialog.show()
            lifecycleScope.launch(Dispatchers.IO) {
                imageClassifierHelper = ImageClassifierHelper(
                    context = this@AddItemActivity,
                    classifierListener = object : ImageClassifierHelper.ClassifierListener{
                        override fun onError(error: String) {
                            progressDialog.dismiss()
                            runOnUiThread { showSnackbar(binding.root,error) }
                        }
                        override fun onResults(results: List<Classifications>?) {
                            runOnUiThread {
                                results?.let { classifications ->
                                    if(classifications.isNotEmpty() && classifications[0].categories.isNotEmpty()){
                                        println(classifications)
                                        val sortedCategories = classifications[0].categories.sortedByDescending { category -> category.score }
                                        analyzedResult = "${sortedCategories[0].label}"
                                        updateCategoryText(analyzedResult)
                                        progressDialog.dismiss()
                                        showSnackbar(binding.root,"Category set to $analyzedResult")
                                    }
                                    Log.d("ANALYZE", "onResults: $classifications")
                                }
                            }
                        }
                    }
                )
                imageClassifierHelper.classifyStaticImage(currentImageUri!!)
            }
        }else{
            showSnackbar(binding.root,"Silahkan masukkan gambar terlebih dahulu")
        }
    }
    private fun updateCategoryText(category: String) {
        binding.txtCategoryAddItem.apply {
            setText(category, false)
            clearFocus()
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
    private fun convertToApiString(category : String) : String{
        if(category == "Fashion"){ return "fashion" }
        else if(category == "Home and Furniture"){ return "home_furniture" }
        else if(category == "Auto and Accessories"){ return "auto_accessories" }
        else if(category == "Electronic"){ return "electronic" }
        return ""
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
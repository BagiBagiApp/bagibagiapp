package com.bagibagi.app.ui.additem

import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.bagibagi.app.data.api.ApiConfig
import com.bagibagi.app.data.model.UserDetailModel
import com.bagibagi.app.data.repo.ItemRepository
import com.bagibagi.app.data.repo.UserRepository
import com.bagibagi.app.data.response.UploadItemResponse
import com.bagibagi.app.helper.reduceFileImage
import com.bagibagi.app.helper.showSnackbar
import com.bagibagi.app.helper.uriToFile
import com.google.gson.Gson
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException

class AddItemViewModel(
    private val itemRepository: ItemRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uploadResult = MutableLiveData<String>("")
    val uploadResult: LiveData<String> = _uploadResult

    private val _showLoading = MutableLiveData<Boolean>(false)
    val showLoading: LiveData<Boolean> = _showLoading

    private val _uploadErrorMessage = MutableLiveData<String>("")
    val uploadErrorMessage: LiveData<String> = _uploadErrorMessage

    @RequiresApi(Build.VERSION_CODES.Q)
    fun uploadItem(
        context: Context,
        imageUri: Uri,
        namaProduk: String,
        desc: String,
        kategori: String,
        qty: Int,
        yearsOfUsage: String,
    ) {
        _showLoading.value = true

        val descriptionBody = desc.toRequestBody("text/plain".toMediaType())
        val namaProdukBody = namaProduk.toRequestBody("text/plain".toMediaType())
        val kategoriBody = kategori.toRequestBody("text/plain".toMediaType())
        val qtyBody = qty.toString().toRequestBody("text/plain".toMediaType())
        val yearsOfUsageBody = yearsOfUsage.toRequestBody("text/plain".toMediaType())
        var idUser: Int? = null
        val userDetailReponse = userRepository.getUserDetail().map {
            val userDetail = it.map {
                UserDetailModel(
                    it.notelp,
                    it.password,
                    it.suksesDonasi,
                    it.id,
                    it.jenisKelamin,
                    it.email,
                    it.tglLahir,
                    it.username,
                    it.alamat
                )
                idUser = it.id
            }
        }
        val imageFile = uriToFile(imageUri, context).reduceFileImage()
        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
        val multipartBody =
            MultipartBody.Part.createFormData("foto", imageFile.name, requestImageFile)
        val idUserBody = idUser.toString().toRequestBody("text/plain".toMediaType())
        viewModelScope.launch {
            try {
                itemRepository.uploadItem(
                    namaProdukBody,
                    descriptionBody,
                    kategoriBody,
                    qtyBody,
                    yearsOfUsageBody,
                    idUserBody,
                    multipartBody
                )
                _uploadResult.value = "Berhasil upload"
                _showLoading.value = false

            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, UploadItemResponse::class.java)
                _uploadErrorMessage.value = errorResponse.error.toString()
                _showLoading.value = false
            }
        }
    }
}
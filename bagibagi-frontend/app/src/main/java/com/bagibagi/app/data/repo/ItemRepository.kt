package com.bagibagi.app.data.repo

import com.bagibagi.app.data.api.ApiService
import com.bagibagi.app.data.model.UserItemModel
import com.bagibagi.app.data.pref.UserPreference
import com.bagibagi.app.data.response.ProdukItem
import com.bagibagi.app.data.response.UploadItemResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part

class ItemRepository private constructor(private val apiService: ApiService){
    fun getUserItem() : Flow<List<UserItemModel>> = flow{
        val response = apiService.getUserDetail()
        val listResponseItem = response.flatMap { it.produk }
        val listUserItem = listResponseItem.map {
            UserItemModel(
                it.linkFoto.toString(),
                it.namaProduk,
                it.yearsOfUsage,
                it.qty,
                it.pemilik,
                it.kategori,
                it.id,
                it.desc,
                it.status
            )
        }
        emit(listUserItem)
    }

    //fun getAllItem() : Flow<List<>>
    suspend fun uploadItem(
        namaProduk: RequestBody,
        description: RequestBody,
        kategori: RequestBody,
        qty: RequestBody,
        yearsOfUsage: RequestBody,
        IDpemilik: RequestBody,
        file: MultipartBody.Part,
    ) : UploadItemResponse{
        return apiService.uploadItem(namaProduk, description, kategori, qty, yearsOfUsage, IDpemilik, file)
    }
    companion object {
        fun getInstance(apiService: ApiService): ItemRepository =
            ItemRepository(apiService)
    }
}
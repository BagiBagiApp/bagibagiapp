package com.bagibagi.app.data.repo

import com.bagibagi.app.data.api.ApiService
import com.bagibagi.app.data.model.UserItemModel
import com.bagibagi.app.data.pref.UserPreference
import com.bagibagi.app.data.response.ProdukItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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

    companion object {
        private var INSTANCE: ItemRepository? = null
        fun getInstance(apiService: ApiService): ItemRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ItemRepository(apiService)
            }.also { INSTANCE = it }
    }
}
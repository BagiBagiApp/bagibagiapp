package com.bagibagi.app.data.repo

import com.bagibagi.app.data.api.ApiService
import com.bagibagi.app.data.model.HistoryModel
import com.bagibagi.app.data.model.NotificationModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TransactionRepository private constructor(private val  apiService : ApiService) {

    fun getAllNotification() : Flow<List<NotificationModel>> = flow{
        val response = apiService.getAllNotification()
        val data = response.map {
            NotificationModel(
                it.id,
                it.requester,
                it.barangRequester,
                it.recipient,
                it.barangRecipient,
                it.jmlhBarangDibarter,
                it.jmlhBarangDidapat,
                it.status
            )
        }
        emit(data)
    }
    fun getAllHistory() : Flow<List<HistoryModel>> = flow{
        val response = apiService.getAllHistory()
        val data = response.map {
            HistoryModel(
                it.id,
                it.requester,
                it.barangRequester,
                it.recipient,
                it.barangRecipient,
                it.jmlhBarangDibarter,
                it.jmlhBarangDidapat,
                it.status
            )
        }
        emit(data)
    }
    suspend fun requestBarter(
        jmlhBarangDibarter : String,
        jmlhBarangDidapat : String,
        barangRequesterID : String,
        barangRecipientID : String
    ): String {
        val response = apiService.requestBarter(jmlhBarangDibarter,jmlhBarangDidapat, barangRequesterID, barangRecipientID)
        return response.message
    }

    companion object {
        fun getInstance(apiService: ApiService): TransactionRepository =
            TransactionRepository(apiService)
    }
}
package com.bagibagi.app.ui.notification

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagibagi.app.data.model.NotificationModel
import com.bagibagi.app.data.model.UserItemModel
import com.bagibagi.app.data.repo.TransactionRepository
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private val _notificationItem = MutableLiveData<List<NotificationModel>>()
    val notificationItem: LiveData<List<NotificationModel>> get() = _notificationItem

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getAllNotification(){
        viewModelScope.launch {
            try {
                transactionRepository.getAllNotification().collect{
                    _notificationItem.value = it
                }
            }catch (e : Exception){
                _error.value = e.message
                Log.e("NotificationViewModel", "getAllNotification: ${e.message}")
            }
        }
    }
}
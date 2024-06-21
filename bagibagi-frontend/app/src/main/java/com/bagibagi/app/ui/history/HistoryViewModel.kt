package com.bagibagi.app.ui.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagibagi.app.data.model.HistoryModel
import com.bagibagi.app.data.model.UserItemModel
import com.bagibagi.app.data.repo.TransactionRepository
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private val _historyItem = MutableLiveData<List<HistoryModel>>()
    val historyItem: LiveData<List<HistoryModel>> get() = _historyItem

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getAllHistory(){
        viewModelScope.launch {
            try {
                transactionRepository.getAllHistory().collect{
                    _historyItem.value = it
                }
            }catch (e : Exception){
                _error.value = e.message
                Log.e("HistoryViewModel", "getAllNotification: ${e.message}")
            }
        }
    }

}
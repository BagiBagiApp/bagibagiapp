package com.bagibagi.app.ui.barter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagibagi.app.data.model.ItemDetailModel
import com.bagibagi.app.data.repo.ItemRepository
import com.bagibagi.app.data.repo.TransactionRepository
import kotlinx.coroutines.launch

class ConfirmActivityViewModel(
    private val itemRepository: ItemRepository,
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private val _userItem = MutableLiveData<ItemDetailModel>()
    val userItem: LiveData<ItemDetailModel> get() = _userItem

    private val _recipientItem = MutableLiveData<ItemDetailModel>()
    val recipientItem: LiveData<ItemDetailModel> get() = _recipientItem

    private val _response = MutableLiveData<String>()
    val response : LiveData<String> get() = _response

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getUserItem(id : Int){
        viewModelScope.launch {
            try{
                itemRepository.getItemDetail(id).collect {
                    it.map { itemDetail ->
                        _userItem.value = itemDetail
                    }
                }
            }
            catch (e : Exception){
                _error.value = e.message
                Log.e("ConfirmActivityViewModel_UserItem", "Error fetching data: ${e.message}")
            }
        }
    }
    fun getRecipientItem(id : Int){
        viewModelScope.launch {
            try{
                itemRepository.getItemDetail(id).collect {
                    it.map { itemDetail ->
                        _recipientItem.value = itemDetail
                    }
                }
            }
            catch (e : Exception){
                _error.value = e.message
                Log.e("ConfirmActivityViewModel_RecipientItem", "Error fetching data: ${e.message}")
            }
        }
    }
    fun requestBarter(
        barangRequesterID : String,
        barangRecipientID : String
    ){
        viewModelScope.launch {
            try {
                _response.value = transactionRepository.requestBarter(
                    "1",
                    "1",
                    barangRequesterID,
                    barangRecipientID
                )
            }catch (e : Exception){
                _error.value = e.message
                Log.e("ConfirmActivityViewModel_RequestBarter", "Error fetching data: ${e.message}")
            }
        }
    }
}
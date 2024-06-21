package com.bagibagi.app.ui.barter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagibagi.app.data.model.UserItemModel
import com.bagibagi.app.data.repo.ItemRepository
import kotlinx.coroutines.launch

class ChooseItemViewModel(
    private val itemRepository: ItemRepository
) : ViewModel() {

    private val _userItem = MutableLiveData<List<UserItemModel>>()
    val userItem: LiveData<List<UserItemModel>> get() = _userItem

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getUserItem(){
        viewModelScope.launch {
            try{
                itemRepository.getUserItem().collect {
                    _userItem.value = it
                }
            }
            catch (e : Exception){
                _error.value = e.message
                Log.e("UserViewModel_UserItem", "Error fetching data: ${e.message}")
            }
        }
    }
}
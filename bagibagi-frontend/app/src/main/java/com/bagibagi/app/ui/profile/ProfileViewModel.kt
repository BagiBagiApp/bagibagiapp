package com.bagibagi.app.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagibagi.app.data.model.UserDetailModel
import com.bagibagi.app.data.model.UserItemModel
import com.bagibagi.app.data.repo.ItemRepository
import com.bagibagi.app.data.repo.UserRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

class ProfileViewModel(
    private val userRepository: UserRepository,
    private val itemRepository: ItemRepository
) : ViewModel() {

    private val _userDetail = MutableLiveData<List<UserDetailModel>>()
    val userDetail: LiveData<List<UserDetailModel>> get() = _userDetail

    private val _userItem = MutableLiveData<List<UserItemModel>>()
    val userItem: LiveData<List<UserItemModel>> get() = _userItem

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    suspend fun logout(){
        userRepository.logout()
    }

    fun getUserDetail() {
        viewModelScope.launch {
            try {
                userRepository.getUserDetail().collect {
                    _userDetail.value = it
                    Log.d("UserViewModel", "Fetched user details")
                }
            } catch (e: Exception) {
                _error.value = e.message
                Log.e("UserViewModel_UserDetail", "Error fetching data: ${e.message}")
            }
        }
    }

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
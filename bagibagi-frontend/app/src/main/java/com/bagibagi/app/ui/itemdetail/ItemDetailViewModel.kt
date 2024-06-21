package com.bagibagi.app.ui.itemdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagibagi.app.data.model.ItemDetailModel
import com.bagibagi.app.data.repo.ItemRepository
import kotlinx.coroutines.launch

class ItemDetailViewModel(
    private val itemRepository : ItemRepository
) : ViewModel() {

    private val _itemDetail = MutableLiveData<ItemDetailModel>()
    val itemDetail: LiveData<ItemDetailModel> get() = _itemDetail

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getItemDetail(id: Int) {
        viewModelScope.launch {
            try{
                itemRepository.getItemDetail(id).collect{ itemDetails ->
                    itemDetails.map {
                        _itemDetail.value = it
                        Log.d("ItemDetailViewModel", "getItemDetail: $it")
                    }

                }
            }catch (e : Exception){
                _error.value = e.message
            }
        }
    }
}
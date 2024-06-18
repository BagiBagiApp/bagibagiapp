package com.bagibagi.app.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagibagi.app.data.model.OrganizationModel
import com.bagibagi.app.data.model.RecommendationItemModel
import com.bagibagi.app.data.model.UserDetailDashboard
import com.bagibagi.app.data.model.UserDetailModel
import com.bagibagi.app.data.model.UserItemModel
import com.bagibagi.app.data.repo.ItemRepository
import com.bagibagi.app.data.repo.OrgRepository
import com.bagibagi.app.data.repo.UserRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userRepository: UserRepository,
    private val itemRepository: ItemRepository,
    private val organizationRepository: OrgRepository
) : ViewModel() {

    private val _recommendedItems = MutableLiveData<List<RecommendationItemModel>>()
    val recommendedItems: LiveData<List<RecommendationItemModel>> get() = _recommendedItems

    private val _organizations = MutableLiveData<List<OrganizationModel>>()
    val organizations: LiveData<List<OrganizationModel>> get() = _organizations

    private val _userDetail = MutableLiveData<List<UserDetailDashboard>>()
    val userDetail: LiveData<List<UserDetailDashboard>> get() = _userDetail

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getUserDetailDashboard(){
        viewModelScope.launch {
            try{
                userRepository.getUserDetailDashboard().collect{
                    _userDetail.value = it
                }
            }catch (e: Exception){
                _error.value = e.message
                Log.e("HomeViewModel", "getUserDetailDashboard: ${e.message}")
            }
        }
    }
    fun getRecommendedItems(){
        viewModelScope.launch {
            try{
                itemRepository.getAllItem().collect{
                    _recommendedItems.value = it
                }
            }catch (e: Exception){
                _error.value = e.message
                Log.e("HomeViewModel", "getRecommendedItems: ${e.message}")
            }
        }
    }
    fun getOrganizations(){
        viewModelScope.launch {
            try{
                organizationRepository.getAllOrg().collect{
                    _organizations.value = it
                }
            }catch (e: Exception){
                _error.value = e.message
                Log.e("HomeViewModel", "getOrganizations: ${e.message}")
            }
        }
    }
}
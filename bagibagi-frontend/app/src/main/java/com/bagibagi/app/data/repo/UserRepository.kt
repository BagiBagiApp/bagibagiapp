package com.bagibagi.app.data.repo

import android.content.Context
import com.bagibagi.app.data.api.ApiService
import com.bagibagi.app.data.model.UserDetailModel
import com.bagibagi.app.data.model.UserModel
import com.bagibagi.app.data.pref.UserPreference
import com.bagibagi.app.data.response.LoginResponse
import com.bagibagi.app.data.response.SignupResponse
import com.bagibagi.app.di.Injection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class UserRepository private constructor(
    private val apiService: ApiService,
    private var userPreference: UserPreference
) {
    fun getSession() : Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun saveSession(user : UserModel){
        userPreference.saveSession(user)
    }

    suspend fun logout(){
        userPreference.logout()
    }

    fun getUserDetail(): Flow<List<UserDetailModel>> = flow {
        val response = apiService.getUserDetail()
        val userDetailModels = response.map {
            UserDetailModel(
                it.notelp,
                it.password,
                it.suksesDonasi,
                it.id,
                it.jenisKelamin,
                it.email,
                it.tglLahir,
                it.username,
                it.alamat
            )
        }
        emit(userDetailModels)
    }

    companion object{
        fun getInstance(apiService: ApiService, pref: UserPreference) : UserRepository =
            UserRepository(apiService, pref)
    }
}
package com.bagibagi.app.data.repo

import com.bagibagi.app.data.api.ApiService
import com.bagibagi.app.data.model.UserModel
import com.bagibagi.app.data.pref.UserPreference
import com.bagibagi.app.data.response.LoginResponse
import com.bagibagi.app.data.response.SignupResponse
import kotlinx.coroutines.flow.Flow

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

    suspend fun signup(
        fullname: String,
        password: String,
        alamat: String,
        noTelp: String,
        email: String,
        tglLahir: String,
        jenisKelamin: String
    ): SignupResponse {
    return apiService.signup(fullname,password,alamat,noTelp,email,tglLahir,jenisKelamin)
    }


    suspend fun login(username: String,password: String) : LoginResponse {
        return apiService.login(username, password)
    }

    suspend fun logout(){
        userPreference.logout()
    }

    companion object{
        private var instance : UserRepository? = null
        fun getInstance(apiService: ApiService, pref: UserPreference) : UserRepository =
            instance ?: synchronized(this){
                instance ?: UserRepository(apiService, pref)
            }.also { instance = it }
        fun clearInstance() {
            instance = null
        }
    }
}
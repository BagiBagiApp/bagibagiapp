package com.bagibagi.app.data.repo

import com.bagibagi.app.data.api.ApiService
import com.bagibagi.app.data.response.LoginResponse
import com.bagibagi.app.data.response.SignupResponse

class AuthRepository private constructor(private val apiService: ApiService) {

    suspend fun signup(
        fullname: String,
        password: String,
        alamat: String,
        noTelp: String,
        email: String,
        tglLahir: String,
        jenisKelamin: String
    ) : SignupResponse = apiService.signup(fullname,password,alamat,noTelp,email,tglLahir,jenisKelamin)

    suspend fun login(username: String,password: String) : LoginResponse = apiService.login(username, password)

    companion object {
        @Volatile
        private var INSTANCE: AuthRepository? = null

        fun getInstance(apiService: ApiService): AuthRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: AuthRepository(apiService).also { INSTANCE = it }
            }
        }
    }
}
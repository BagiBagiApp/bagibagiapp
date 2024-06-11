package com.bagibagi.app.ui.signup

import androidx.lifecycle.ViewModel
import com.bagibagi.app.data.repo.AuthRepository
import com.bagibagi.app.data.repo.UserRepository
import com.bagibagi.app.data.response.SignupResponse

class SignupViewModel(private val authRepository: AuthRepository) : ViewModel() {

    suspend fun signup(
        fullname: String,
        password: String,
        alamat: String,
        noTelp: String,
        email: String,
        tglLahir: String,
        jenisKelamin: String
    ) : SignupResponse = authRepository.signup(fullname,password,alamat,noTelp,email,tglLahir,jenisKelamin)
}
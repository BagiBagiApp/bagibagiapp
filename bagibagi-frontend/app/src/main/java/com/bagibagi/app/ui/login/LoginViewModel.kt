package com.bagibagi.app.ui.login

import androidx.lifecycle.ViewModel
import com.bagibagi.app.data.model.UserModel
import com.bagibagi.app.data.repo.AuthRepository
import com.bagibagi.app.data.repo.UserRepository
import com.bagibagi.app.data.response.LoginResponse

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
    ) : ViewModel() {
    suspend fun saveSession(user : UserModel){
        userRepository.saveSession(user)
    }
    suspend fun login(email: String, password: String): LoginResponse {
        return authRepository.login(email, password)
    }
}
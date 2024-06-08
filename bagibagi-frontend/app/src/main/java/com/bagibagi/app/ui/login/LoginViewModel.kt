package com.bagibagi.app.ui.login

import androidx.lifecycle.ViewModel
import com.bagibagi.app.data.repo.UserRepository
import com.bagibagi.app.data.response.LoginResponse

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    suspend fun login(email: String, password: String): LoginResponse {
        return userRepository.login(email, password)
    }
}
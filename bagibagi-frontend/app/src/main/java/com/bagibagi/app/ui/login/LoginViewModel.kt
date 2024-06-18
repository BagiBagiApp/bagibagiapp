package com.bagibagi.app.ui.login

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagibagi.app.data.model.UserModel
import com.bagibagi.app.data.repo.AuthRepository
import com.bagibagi.app.data.repo.UserRepository
import com.bagibagi.app.data.response.LoginResponse
import com.bagibagi.app.di.Injection
import com.bagibagi.app.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) :
    ViewModel() {

    private val _loginResult = MutableLiveData<Boolean?>(null)
    val loginResult: LiveData<Boolean?> = _loginResult

    private val _loginErrorMessage = MutableLiveData<String>("")
    val loginErrorMessage: LiveData<String> = _loginErrorMessage

    suspend fun saveSession(user: UserModel) {
        userRepository.saveSession(user)
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val loginResponse = authRepository.login(email, password)
                if (loginResponse.token != null) {
                    Log.d("LoginActivity", "Saving token: ${loginResponse.token}")
                    saveSession(UserModel(loginResponse.token))
                    _loginResult.value = true
                } else if (loginResponse.message != null) {
                    _loginErrorMessage.value = loginResponse.message.toString()
                    _loginResult.value = false
                }
            } catch (e: HttpException) {
                if (e.code() == 400) {
                    val errorResponse = e.response()?.errorBody()?.string()
                    val jsonError = Gson().fromJson(errorResponse, LoginResponse::class.java)
                    _loginErrorMessage.value = jsonError.message ?: "Unknown error"
                } else {
                    _loginErrorMessage.value = e.message()
                }
                _loginResult.value = false
            }
        }
    }
}
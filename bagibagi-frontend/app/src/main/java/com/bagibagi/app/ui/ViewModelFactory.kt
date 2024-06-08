package com.bagibagi.app.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bagibagi.app.data.repo.UserRepository
import com.bagibagi.app.di.Injection
import com.bagibagi.app.ui.login.LoginViewModel
import com.bagibagi.app.ui.signup.SignupViewModel
import com.bagibagi.app.ui.welcome.WelcomeViewModel

class ViewModelFactory(
    private val userRepository: UserRepository? = null
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(WelcomeViewModel::class.java) -> {
                if (userRepository != null) {
                    @Suppress("UNCHECKED_CAST")
                    return WelcomeViewModel(userRepository) as T
                } else {
                    throw IllegalArgumentException("Missing dependencies for WelcomeViewModel")
                }
            }
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                if (userRepository != null) {
                    @Suppress("UNCHECKED_CAST")
                    return SignupViewModel(userRepository) as T
                } else {
                    throw IllegalArgumentException("Missing dependencies for SignupViewModel")
                }
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                if (userRepository != null) {
                    @Suppress("UNCHECKED_CAST")
                    return LoginViewModel(userRepository) as T
                } else {
                    throw IllegalArgumentException("Missing dependencies for LoginViewModel")
                }
            }
            /*
            modelClass.isAssignableFrom(MyViewModel::class.java) -> {
            if (userRepository != null && productRepository != null) {
            @Suppress("UNCHECKED_CAST")
            return MyViewModel(userRepository, productRepository) as T
            } else {
            throw IllegalArgumentException("Missing dependencies for MyViewModel")
            }
            }
            */
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
    companion object {
        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context) : ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideUserRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}
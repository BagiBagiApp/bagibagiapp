package com.bagibagi.app.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bagibagi.app.data.repo.AuthRepository
import com.bagibagi.app.data.repo.ItemRepository
import com.bagibagi.app.data.repo.UserRepository
import com.bagibagi.app.di.Injection
import com.bagibagi.app.ui.login.LoginViewModel
import com.bagibagi.app.ui.profile.ProfileViewModel
import com.bagibagi.app.ui.signup.SignupViewModel
import com.bagibagi.app.ui.welcome.WelcomeViewModel

class ViewModelFactory(
    private val userRepository: UserRepository? = null,
    private val authRepository: AuthRepository? = null,
    private val itemRepository: ItemRepository? = null
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
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                if (userRepository != null && authRepository != null) {
                    @Suppress("UNCHECKED_CAST")
                    return LoginViewModel(authRepository,userRepository) as T
                } else {
                    throw IllegalArgumentException("Missing dependencies for LoginViewModel")
                }
            }
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                if (authRepository != null) {
                    @Suppress("UNCHECKED_CAST")
                    return SignupViewModel(authRepository) as T
                } else {
                    throw IllegalArgumentException("Missing dependencies for SignupViewModel")
                }
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                if (userRepository != null && itemRepository != null) {
                    @Suppress("UNCHECKED_CAST")
                    return ProfileViewModel(userRepository,itemRepository) as T
                } else {
                    throw IllegalArgumentException("Missing dependencies for ProfileViewModel")
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
                    INSTANCE = ViewModelFactory(
                        Injection.provideUserRepository(context),
                        Injection.provideAuthRepository(),
                        Injection.provideItemRepository(context)
                    )
                }
            }
            return INSTANCE as ViewModelFactory
        }
        fun refreshInstance(context: Context): ViewModelFactory {
            Injection.refreshUserRepository(context)
            return getInstance(context)
        }
    }
}
package com.bagibagi.app.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bagibagi.app.data.repo.AuthRepository
import com.bagibagi.app.data.repo.ItemRepository
import com.bagibagi.app.data.repo.OrgRepository
import com.bagibagi.app.data.repo.TransactionRepository
import com.bagibagi.app.data.repo.UserRepository
import com.bagibagi.app.di.Injection
import com.bagibagi.app.ui.additem.AddItemViewModel
import com.bagibagi.app.ui.history.HistoryViewModel
import com.bagibagi.app.ui.home.HomeViewModel
import com.bagibagi.app.ui.itemdetail.ItemDetailViewModel
import com.bagibagi.app.ui.itemdetailuser.UserItemDetailViewModel
import com.bagibagi.app.ui.login.LoginViewModel
import com.bagibagi.app.ui.notification.NotificationViewModel
import com.bagibagi.app.ui.profile.ProfileViewModel
import com.bagibagi.app.ui.signup.SignupViewModel
import com.bagibagi.app.ui.welcome.WelcomeViewModel

class ViewModelFactory(
    private val userRepository: UserRepository? = null,
    private val authRepository: AuthRepository? = null,
    private val itemRepository: ItemRepository? = null,
    private val organizationRepository: OrgRepository? = null,
    private val transactionRepository: TransactionRepository? = null
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
            modelClass.isAssignableFrom(AddItemViewModel::class.java) -> {
                if (userRepository != null && itemRepository != null) {
                    @Suppress("UNCHECKED_CAST")
                    return AddItemViewModel(itemRepository,userRepository) as T
                } else {
                    throw IllegalArgumentException("Missing dependencies for ProfileViewModel")
                }
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                if (userRepository != null && itemRepository != null && organizationRepository != null) {
                    @Suppress("UNCHECKED_CAST")
                    return HomeViewModel(userRepository,itemRepository,organizationRepository) as T
                } else {
                    throw IllegalArgumentException("Missing dependencies for ProfileViewModel")
                }
            }
            modelClass.isAssignableFrom(UserItemDetailViewModel::class.java) -> {
                if (itemRepository != null) {
                    @Suppress("UNCHECKED_CAST")
                    return UserItemDetailViewModel(itemRepository) as T
                } else {
                    throw IllegalArgumentException("Missing dependencies for ProfileViewModel")
                }
            }
            modelClass.isAssignableFrom(ItemDetailViewModel::class.java) -> {
                if (itemRepository != null) {
                    @Suppress("UNCHECKED_CAST")
                    return ItemDetailViewModel(itemRepository) as T
                } else {
                    throw IllegalArgumentException("Missing dependencies for ProfileViewModel")
                }
            }
            modelClass.isAssignableFrom(NotificationViewModel::class.java) -> {
                if (transactionRepository != null) {
                    @Suppress("UNCHECKED_CAST")
                    return NotificationViewModel(transactionRepository) as T
                } else {
                    throw IllegalArgumentException("Missing dependencies for ProfileViewModel")
                }
            }
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                if (transactionRepository != null) {
                    @Suppress("UNCHECKED_CAST")
                    return HistoryViewModel(transactionRepository) as T
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
        fun getInstance(context: Context): ViewModelFactory {
            return ViewModelFactory(
                Injection.provideUserRepository(context),
                Injection.provideAuthRepository(),
                Injection.provideItemRepository(context),
                Injection.provideOrganizationRepository(context),
                Injection.provideTransactionRepository(context)
            )
        }
    }
}
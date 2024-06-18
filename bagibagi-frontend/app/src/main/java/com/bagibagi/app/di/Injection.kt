package com.bagibagi.app.di

import android.content.Context
import android.util.Log
import com.bagibagi.app.data.api.ApiConfig
import com.bagibagi.app.data.pref.UserPreference
import com.bagibagi.app.data.pref.dataStore
import com.bagibagi.app.data.repo.AuthRepository
import com.bagibagi.app.data.repo.ItemRepository
import com.bagibagi.app.data.repo.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideAuthRepository(): AuthRepository {
        val apiService = ApiConfig.getApiService()
        return AuthRepository.getInstance(apiService)
    }

    fun provideUserRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiServiceWithToken(user.token)
        return UserRepository.getInstance(apiService, pref)
    }

    fun provideItemRepository(context: Context) : ItemRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiServiceWithToken(user.token)
        return ItemRepository.getInstance(apiService)
    }
}
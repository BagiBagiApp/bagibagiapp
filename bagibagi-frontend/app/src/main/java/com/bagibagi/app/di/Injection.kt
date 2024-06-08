package com.bagibagi.app.di

import android.content.Context
import com.bagibagi.app.data.api.ApiConfig
import com.bagibagi.app.data.pref.UserPreference
import com.bagibagi.app.data.pref.dataStore
import com.bagibagi.app.data.repo.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideUserRepository(context: Context) : UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService,pref)
    }
}
package com.bagibagi.app.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val token : String,
    val isLogin : Boolean = false
) : Parcelable
package com.bagibagi.app.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDetailDashboard(
    val sukses_donasi : Int,
    val sukses_barter : Int
) : Parcelable

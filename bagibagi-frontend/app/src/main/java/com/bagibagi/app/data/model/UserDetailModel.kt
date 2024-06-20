package com.bagibagi.app.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDetailModel(
    val notelp : String,
    val password : String,
    val suksesDonasi : Int,
    val id : Int,
    val jenisKelamin : String,
    val email : String,
    val tglLahir : String,
    val username : String,
    val alamat : String,
) : Parcelable
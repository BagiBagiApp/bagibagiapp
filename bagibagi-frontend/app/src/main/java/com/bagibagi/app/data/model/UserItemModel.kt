package com.bagibagi.app.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserItemModel(
    val linkFoto : String? = "",
    val namaProduk : String,
    val yearsOfUsage : String,
    val qty : Int,
    val pemilik : Int,
    val kategori : String,
    val id : Int,
    val desc : String,
    val status : String? = ""
) : Parcelable

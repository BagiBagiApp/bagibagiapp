package com.bagibagi.app.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemDetailModel(
    val id: Int,
    val nama_produk: String,
    val desc: String,
    val kategori: String,
    val qty: Int,
    val status: String? = "",
    val years_of_usage: String,
    val pemilik: String,
    val alamat : String,
    val link_foto: String? = ""
) : Parcelable
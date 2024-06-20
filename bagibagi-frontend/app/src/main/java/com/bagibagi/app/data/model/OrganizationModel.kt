package com.bagibagi.app.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrganizationModel(
    val id : Int,
    val nama: String,
    val desc: String,
    val kontak : String? = "",
    val alamat: String,
    val link_foto : String? = ""
) : Parcelable
package com.bagibagi.app.data.model

data class OrganizationModel(
    val id : Int,
    val nama: String,
    val desc: String,
    val kontak : String? = "",
    val alamat: String,
    val link_foto : String? = ""
)
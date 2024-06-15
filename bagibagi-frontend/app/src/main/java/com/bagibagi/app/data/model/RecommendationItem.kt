package com.bagibagi.app.data.model

data class RecommendationItem(
    val id: Int,
    val nama_produk: String,
    val desc: String,
    val kategori: String,
    val qty: Int,
    val status: String,
    val years_of_usage: String,
    val pemilik: Int,
    val link_foto: String?
)
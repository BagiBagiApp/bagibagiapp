package com.bagibagi.app.data.response

import com.google.gson.annotations.SerializedName

data class RecommendationsItem(

	@field:SerializedName("link_foto")
	val linkFoto: String,

	@field:SerializedName("nama_produk")
	val namaProduk: String,

	@field:SerializedName("years_of_usage")
	val yearsOfUsage: String,

	@field:SerializedName("qty")
	val qty: Int,

	@field:SerializedName("pemilik")
	val pemilik: Int,

	@field:SerializedName("kategori")
	val kategori: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("desc")
	val desc: String,

	@field:SerializedName("status")
	val status: String
)

data class GetRecommendationResponse(

	@field:SerializedName("recommendations")
	val recommendations: List<RecommendationsItem>
)

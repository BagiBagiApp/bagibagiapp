package com.bagibagi.app.data.response

import com.google.gson.annotations.SerializedName

data class UploadItemResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("statusText")
	val statusText: String,

	@field:SerializedName("count")
	val count: Any,

	@field:SerializedName("error")
	val error: Any,

	@field:SerializedName("status")
	val status: Int
)

data class DataItem(

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

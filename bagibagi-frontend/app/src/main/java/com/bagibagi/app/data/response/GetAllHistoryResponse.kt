package com.bagibagi.app.data.response

import com.google.gson.annotations.SerializedName

data class GetAllHistoryResponse(

	@field:SerializedName("GetAllHistoryResponse")
	val getAllHistoryResponse: List<GetAllHistoryResponseItem>
)

data class GetAllHistoryResponseItem(

	@field:SerializedName("requester")
	val requester: HistoryRequester,

	@field:SerializedName("jmlh_barang_dibarter")
	val jmlhBarangDibarter: Int,

	@field:SerializedName("recipient")
	val recipient: HistoryRecipient,

	@field:SerializedName("jmlh_barang_didapat")
	val jmlhBarangDidapat: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("barang_requester")
	val barangRequester: HistoryBarangRequester,

	@field:SerializedName("barang_recipient")
	val barangRecipient: HistoryBarangRecipient,

	@field:SerializedName("status")
	val status: String
)

data class HistoryBarangRequester(

	@field:SerializedName("link_foto")
	val linkFoto: Any,

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

data class HistoryRequester(

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("alamat")
	val alamat: String
)

data class HistoryBarangRecipient(

	@field:SerializedName("link_foto")
	val linkFoto: Any,

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

data class HistoryRecipient(

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("alamat")
	val alamat: String
)

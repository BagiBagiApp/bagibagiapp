package com.bagibagi.app.data.response

import com.google.gson.annotations.SerializedName

data class GetAllNotificationResponse(

	@field:SerializedName("GetAllNotificationResponse")
	val getAllNotificationResponse: List<GetAllNotificationResponseItem>
)

data class GetAllNotificationResponseItem(

	@field:SerializedName("requester")
	val requester: NotifRequester,

	@field:SerializedName("jmlh_barang_dibarter")
	val jmlhBarangDibarter: Int,

	@field:SerializedName("recipient")
	val recipient: NotifRecipient,

	@field:SerializedName("jmlh_barang_didapat")
	val jmlhBarangDidapat: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("barang_requester")
	val barangRequester: NotifBarangRequester,

	@field:SerializedName("barang_recipient")
	val barangRecipient: NotifBarangRecipient,

	@field:SerializedName("status")
	val status: String
)

data class NotifRecipient(

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("alamat")
	val alamat: String
)

data class NotifRequester(

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("alamat")
	val alamat: String
)

data class NotifBarangRequester(

	@field:SerializedName("link_foto")
	val linkFoto: String? = "",

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

data class NotifBarangRecipient(

	@field:SerializedName("link_foto")
	val linkFoto: String? = "",

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

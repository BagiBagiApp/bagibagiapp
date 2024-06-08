package com.bagibagi.app.data.response

import com.google.gson.annotations.SerializedName

data class GetUserDetailResponse(

	@field:SerializedName("data")
	val data: Data
)

data class DataItem(

	@field:SerializedName("notelp")
	val notelp: String,

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("sukses_donasi")
	val suksesDonasi: Int,

	@field:SerializedName("sukses_barter")
	val suksesBarter: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("jenis_kelamin")
	val jenisKelamin: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("tgl_lahir")
	val tglLahir: String,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("alamat")
	val alamat: String
)

data class Data(

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

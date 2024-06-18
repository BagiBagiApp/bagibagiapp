package com.bagibagi.app.data.response

import com.google.gson.annotations.SerializedName

data class GetUserDetailDashboardResponse(

	@field:SerializedName("GetUserDetailDashboardResponse")
	val getUserDetailDashboardResponse: List<GetUserDetailDashboardResponseItem>
)

data class GetUserDetailDashboardResponseItem(

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

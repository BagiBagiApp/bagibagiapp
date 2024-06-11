package com.bagibagi.app.data.response

import com.google.gson.annotations.SerializedName

data class GetUserDetailResponse(

	@field:SerializedName("GetUserDetailResponse")
	val getUserDetailResponse: List<GetUserDetailResponseItem>
)

data class GetUserDetailResponseItem(

	@field:SerializedName("notelp")
	val notelp: String,

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("produk")
	val produk: List<ProdukItem>,

	@field:SerializedName("sukses_donasi")
	val suksesDonasi: Int,

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

data class ProdukItem(

	@field:SerializedName("link_foto")
	val linkFoto: Any? = "",

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

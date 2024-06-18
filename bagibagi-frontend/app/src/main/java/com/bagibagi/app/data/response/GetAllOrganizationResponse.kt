package com.bagibagi.app.data.response

import com.google.gson.annotations.SerializedName

data class GetAllOrganizationResponse(

	@field:SerializedName("GetAllOrganizationResponse")
	val getAllOrganizationResponse: List<GetAllOrganizationResponseItem>
)

data class GetAllOrganizationResponseItem(

	@field:SerializedName("link_foto")
	val linkFoto: String? = "",

	@field:SerializedName("kontak")
	val kontak: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("desc")
	val desc: String,

	@field:SerializedName("alamat")
	val alamat: String
)

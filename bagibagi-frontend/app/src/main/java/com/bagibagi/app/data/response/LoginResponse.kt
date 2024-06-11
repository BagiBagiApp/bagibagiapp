package com.bagibagi.app.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("token")
    val token: String? = null,

	@field:SerializedName("message")
	val message: String? = null
)


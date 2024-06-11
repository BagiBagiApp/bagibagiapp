package com.bagibagi.app.data.response

import com.google.gson.annotations.SerializedName

data class SignupResponse(
	@field:SerializedName("message")
	val message: String
)

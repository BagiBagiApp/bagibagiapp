package com.bagibagi.app.data.api

import com.bagibagi.app.data.response.GetUserDetailResponse
import com.bagibagi.app.data.response.LoginResponse
import com.bagibagi.app.data.response.SignupResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("users/register")
    suspend fun signup(
        @Field("username") fullname : String,
        @Field("password") password : String,
        @Field("alamat") alamat : String,
        @Field("notelp") noTelp : String,
        @Field("email") email : String,
        @Field("tgl_lahir") tglLahir : String,
        @Field("jenis_kelamin") jenisKelamin : String
    ) : SignupResponse

    @FormUrlEncoded
    @POST("users/login")
    suspend fun login(
        @Field("username") username : String,
        @Field("password") password: String
    ) : LoginResponse

    @GET("users/{id}")
    suspend fun getUserDetail(
        @Path("id") id: Int,
    ): GetUserDetailResponse
}
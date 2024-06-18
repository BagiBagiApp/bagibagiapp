package com.bagibagi.app.data.api

import com.bagibagi.app.data.response.DataItemAll
import com.bagibagi.app.data.response.GetAllOrganizationResponse
import com.bagibagi.app.data.response.GetAllOrganizationResponseItem
import com.bagibagi.app.data.response.GetAllProductResponse
import com.bagibagi.app.data.response.GetUserDetailDashboardResponseItem
import com.bagibagi.app.data.response.GetUserDetailResponseItem
import com.bagibagi.app.data.response.LoginResponse
import com.bagibagi.app.data.response.SignupResponse
import com.bagibagi.app.data.response.UploadItemResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

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

    @Multipart
    @POST("product/upload")
    suspend fun uploadItem(
        @Part("nama_produk") namaProduk: RequestBody,
        @Part("desc") description: RequestBody,
        @Part("kategori") kategori: RequestBody,
        @Part("qty") qty: RequestBody,
        @Part("status") status : RequestBody,
        @Part("years_of_usage") yearsOfUsage: RequestBody,
        @Part("pemilik") IDpemilik: RequestBody,
        @Part file: MultipartBody.Part,
    ): UploadItemResponse

    @GET("/users/getProfile")
    suspend fun getUserDetail() : List<GetUserDetailResponseItem>

    @GET("/users/userDashboard")
    suspend fun getUserDashboard() : List<GetUserDetailDashboardResponseItem>

    @GET("product/allProducts")
    suspend fun getAllItems(): GetAllProductResponse

    @GET("org/allOrg")
    suspend fun getAllOrganizations(): List<GetAllOrganizationResponseItem>
}
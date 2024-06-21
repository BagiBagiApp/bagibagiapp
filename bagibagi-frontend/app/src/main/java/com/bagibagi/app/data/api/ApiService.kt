package com.bagibagi.app.data.api

import com.bagibagi.app.data.response.DataItemDetail
import com.bagibagi.app.data.response.GetAllHistoryResponse
import com.bagibagi.app.data.response.GetAllHistoryResponseItem
import com.bagibagi.app.data.response.GetAllNotificationResponse
import com.bagibagi.app.data.response.GetAllNotificationResponseItem
import com.bagibagi.app.data.response.GetAllOrganizationResponseItem
import com.bagibagi.app.data.response.GetAllProductResponse
import com.bagibagi.app.data.response.GetItemDetailResponse
import com.bagibagi.app.data.response.GetRecommendationResponse
import com.bagibagi.app.data.response.GetUserDetailDashboardResponseItem
import com.bagibagi.app.data.response.GetUserDetailResponseItem
import com.bagibagi.app.data.response.LoginResponse
import com.bagibagi.app.data.response.RequestBarterResponse
import com.bagibagi.app.data.response.SearchItemResponse
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
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // TODO : For Final Production remove sb
    @FormUrlEncoded
    @POST("users/registersb")
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
    @POST("users/loginsb")
    suspend fun login(
        @Field("username") username : String,
        @Field("password") password: String
    ) : LoginResponse

    @FormUrlEncoded
    @POST("exchange/reqBartersb")
    suspend fun requestBarter(
        @Field("jmlh_barang_dibarter") jmlhBarangDibarter : String,
        @Field("jmlh_barang_didapat") jmlhBarangDidapat : String,
        @Field("barang_requester") barangRequesterID : String,
        @Field("barang_recipient") barangRecipientID : String
    ): RequestBarterResponse

    @Multipart
    @POST("product/uploadsb")
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

    @GET("/users/getProfilesb")
    suspend fun getUserDetail() : List<GetUserDetailResponseItem>

    @GET("/users/userDashboardsb")
    suspend fun getUserDashboard() : List<GetUserDetailDashboardResponseItem>

    @GET("product/allProductssb")
    suspend fun getAllItems(): GetAllProductResponse

    @GET("ml/recommendationssb")
    suspend fun getRecommendations(): GetRecommendationResponse

    @GET("product/detailssb/{itemID}")
    suspend fun getItemDetail(
        @Path("itemID") itemID: Int
    ) : GetItemDetailResponse

    @GET("/product/allProductssb")
    suspend fun searchItem(
        @Query("nama_produk") productName: String
    ): SearchItemResponse

    @GET("org/allOrgsb")
    suspend fun getAllOrganizations(): List<GetAllOrganizationResponseItem>

    @GET("exchange/bartersb")
    suspend fun getAllNotification(): List<GetAllNotificationResponseItem>

    @GET("exchange/requestedBartersb")
    suspend fun getAllHistory(): List<GetAllHistoryResponseItem>


}
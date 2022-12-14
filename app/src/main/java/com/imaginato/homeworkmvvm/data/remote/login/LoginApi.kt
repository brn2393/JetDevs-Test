package com.imaginato.homeworkmvvm.data.remote.login

import com.imaginato.homeworkmvvm.data.remote.login.request.LoginRequest
import com.imaginato.homeworkmvvm.data.remote.login.response.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface LoginApi {
    @Headers("Content-Type: application/json")
    @POST
    suspend fun performLoginAsync(
        @Url url: String,
        @Header("IMSI") imsi: String,
        @Header("IMEI") imei: String,
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>
}
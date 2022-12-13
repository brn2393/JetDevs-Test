package com.imaginato.homeworkmvvm.data.remote.login

import com.imaginato.homeworkmvvm.data.remote.login.response.LoginResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface LoginApi {
    @GET
    suspend fun performLoginAsync(@Url url: String): LoginResponse
}
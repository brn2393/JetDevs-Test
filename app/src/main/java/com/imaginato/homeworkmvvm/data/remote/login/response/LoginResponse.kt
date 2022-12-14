package com.imaginato.homeworkmvvm.data.remote.login.response

data class LoginResponse(
    val `data`: Data?,
    val errorCode: String,
    val errorMessage: String
)
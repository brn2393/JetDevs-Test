package com.imaginato.homeworkmvvm.data.remote.login.response

import com.google.gson.annotations.SerializedName

data class LoginResponse constructor(
    @SerializedName("ip_addr")
    var ipAddress: String?
)
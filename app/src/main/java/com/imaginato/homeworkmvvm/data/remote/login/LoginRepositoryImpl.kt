package com.imaginato.homeworkmvvm.data.remote.login

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRepositoryImpl constructor(
    private var api: LoginApi
) : LoginRepository {
    companion object {
        private const val URL_GET_PUBLIC_IP = "https://ifconfig.me/all.json"
        private const val NOTHING_GET = "Nothing get!"
    }

    override fun performLogin() = flow {
        val response = api.performLoginAsync(URL_GET_PUBLIC_IP)
        emit(response.ipAddress ?: NOTHING_GET)
    }.flowOn(Dispatchers.IO)
}
package com.imaginato.homeworkmvvm.data.remote.login

import com.imaginato.homeworkmvvm.data.local.login.Login
import com.imaginato.homeworkmvvm.data.local.login.LoginDao
import com.imaginato.homeworkmvvm.data.remote.login.request.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRepositoryImpl constructor(
    private var api: LoginApi,
    private var loginDao: LoginDao
) : LoginRepository {
    companion object {
        private const val URL_LOGIN = "https://private-222d3-homework5.apiary-mock.com/api/login"
        private const val HEADER_X_ACC = "X-Acc"
        private const val IMSI = "357175048449937"
        private const val IMEI = "510110406068589"
    }

    override fun performLogin(username: String, password: String) = flow {
        val request = LoginRequest(username, password)
        try {
            val response = api.performLoginAsync(URL_LOGIN, IMSI, IMEI, request)
            if (response.isSuccessful) {
                val xAcc = response.headers()[HEADER_X_ACC] ?: ""

                response.body()?.data?.let {
                    Login(
                        xAcc = xAcc,
                        isDeleted = it.isDeleted,
                        userId = it.userId,
                        userName = it.userName
                    )
                }?.also { login ->
                    loginDao.insertLogin(login)
                }

                emit(response.body()?.errorMessage)
            }
        } catch (e: Exception) {
            emit(e.message.toString())
        }
    }.flowOn(Dispatchers.IO)
}
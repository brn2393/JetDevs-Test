package com.imaginato.homeworkmvvm.ui.login

import com.imaginato.homeworkmvvm.data.remote.login.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun provideLoginRepo(): LoginRepository {
    return FakeLoginRepository()
}

class FakeLoginRepository : LoginRepository {
    override fun performLogin(username: String, password: String): Flow<String?> = flow {
        emit("Login by Bhushan")
    }
}
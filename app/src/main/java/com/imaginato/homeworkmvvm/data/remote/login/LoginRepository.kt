package com.imaginato.homeworkmvvm.data.remote.login

import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun performLogin(username: String, password: String): Flow<String?>
}
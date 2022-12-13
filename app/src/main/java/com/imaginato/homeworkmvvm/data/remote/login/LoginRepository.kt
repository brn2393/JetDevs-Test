package com.imaginato.homeworkmvvm.data.remote.login

import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun performLogin(): Flow<String>
}
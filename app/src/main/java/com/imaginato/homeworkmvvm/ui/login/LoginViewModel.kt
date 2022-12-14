package com.imaginato.homeworkmvvm.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.imaginato.homeworkmvvm.data.remote.login.LoginRepository
import com.imaginato.homeworkmvvm.ui.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.inject

@KoinApiExtension
class LoginViewModel : BaseViewModel() {
    private val repository: LoginRepository by inject()
    private var _progress: MutableLiveData<Boolean> = MutableLiveData()
    val progress: LiveData<Boolean>
        get() = _progress
    private var _result: MutableLiveData<String> = MutableLiveData()
    val result: LiveData<String>
        get() = _result

    fun performLogin(username: String, password: String) {
        viewModelScope.launch {
            repository.performLogin(username, password)
                .onStart {
                    _progress.value = true
                }.catch {
                    _progress.value = false
                    _result.value = "Login failed!"
                }.onCompletion {
                }.collect {
                    _progress.value = false
                    _result.value = it
                }
        }
    }
}
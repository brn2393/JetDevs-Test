package com.imaginato.homeworkmvvm.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.imaginato.homeworkmvvm.databinding.ActivityLoginBinding
import com.imaginato.homeworkmvvm.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class LoginActivity : BaseActivity() {
    private val viewModel by viewModel<LoginViewModel>()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.editText?.text.toString()
            val password = binding.edtPassword.editText?.text.toString()
            viewModel.performLogin(username, password)
        }
        initObserve()
    }

    private fun initObserve() {
        viewModel.progress.observe(this) {
            binding.pbLoading.isVisible = it
        }
        viewModel.result.observe(this) {
            Toast.makeText(this@LoginActivity, it, Toast.LENGTH_LONG).show()
        }
    }
}
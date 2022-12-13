package com.imaginato.homeworkmvvm.ui.login

import android.os.Bundle
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
            viewModel.performLogin()
        }
        initObserve()
    }

    private fun initObserve() {
        viewModel.progress.observe(this) {
            binding.pbLoading.isVisible = it
        }
    }
}
package com.ds.baselogin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ds.baselogin.databinding.ActivityLoginBinding
import com.ds.login.LoginComponentsProvider
import com.ds.login.LoginValidator
import com.ds.retrofit.ApiViewModel
import com.ds.retrofit.network.*
import com.ds.retrofit.repository.ApiRepositoryImpl

class LoginActivity : AppCompatActivity(), LoginComponentsProvider {

    private val binding: ActivityLoginBinding by lazy {
        DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.activity_login,
            null,
            false
        )
    }

    private val apiService by lazy { ServiceGenerator<ApiService>(this, "baseUrl", null) }
    private val repository by lazy {  ApiRepositoryImpl(apiService) }
    private val viewModelFactory by lazy { ViewModelFactory(repository) }
    private val viewModel by lazy { ViewModelProvider(this, viewModelFactory)[ApiViewModel::class.java] }


    private val loginValidator by lazy { LoginValidator(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            loginValidator.validateLoginData { email, password ->
//                login(LoginRequest(email,password))
            }
        }

        binding.signUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    class LoginRequest(email: String, password: String) : ApiRequest()

    private fun login(request: LoginRequest) {
        viewModel.post<Any>("apiUrl", request).observe(this) {
            when (it?.status) {
                Status.LOADING -> {}
                Status.SUCCESS -> {}
                Status.ERROR -> {}
            }
        }
    }

    override val email: EditText
        get() = binding.emailInput

    override val password: EditText
        get() = binding.inputPassword
}
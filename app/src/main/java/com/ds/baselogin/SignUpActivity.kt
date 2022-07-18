package com.ds.baselogin

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ds.baselogin.databinding.ActivitySignUpBinding
import com.ds.login.SignUpComponentsProvider
import com.ds.login.SignUpValidator
import com.ds.retrofit.ApiViewModel
import com.ds.retrofit.network.ApiService
import com.ds.retrofit.network.ServiceGenerator
import com.ds.retrofit.network.Status
import com.ds.retrofit.network.ViewModelFactory
import com.ds.retrofit.repository.ApiRepositoryImpl


class SignUpActivity : AppCompatActivity(), SignUpComponentsProvider {
    private val binding: ActivitySignUpBinding by lazy {
        DataBindingUtil.inflate(
            LayoutInflater.from(
                this
            ), R.layout.activity_sign_up, null, false
        )
    }


    private val apiService = ServiceGenerator<ApiService>(this, "", null)
    private val repository = ApiRepositoryImpl(apiService)
    private val viewModelFactory = ViewModelFactory(repository)
    private val viewModel = ViewModelProvider(this, viewModelFactory)[ApiViewModel::class.java]


    private val signUpValidator by lazy { SignUpValidator(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.submitButton.setOnClickListener {
            signUpValidator.validateSignUpData { firstName, lastName, email, password ->
                Toast.makeText(this, "$firstName $lastName $email $password", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun signUp(first: String, lastname: String, email: String, password: String) {
        viewModel.get<Any>("apiUrl").observe(this){
            when(it?.status){
                Status.LOADING -> {}
                Status.SUCCESS -> {}
                Status.ERROR -> {}
            }
        }
    }

    override val firstName: EditText
        get() = binding.firstName

    override val lastName: EditText
        get() = binding.lastName

    override val email: EditText
        get() = binding.email

    override val password: EditText
        get() = binding.password

    override val secondPassword: EditText
        get() = binding.reEnterPassword
}
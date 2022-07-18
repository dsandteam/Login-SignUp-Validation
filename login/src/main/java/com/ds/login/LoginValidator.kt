package com.ds.login

import android.util.Patterns
import com.ds.login.LoginConstants.emailError
import com.ds.login.LoginConstants.invalidEmailError
/**
 *
 * Created By Amir Fury on 18 July 2022
 *
 * **/
class LoginValidator(private val provider: LoginComponentsProvider) {

    fun validateLoginData(onLoginDataValid: (email: String, password: String) -> Unit) =
        with(provider) {
            removeError(email,password)
            email?.let {
                val input = it.text.toString()
                if (input.isEmpty()) {
                    it.requestFocus()
                    it.setErrorOnEdit(emailError)
                    return@with
                } else if (!Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
                    it.requestFocus()
                    it.setErrorOnEdit(invalidEmailError)
                    return@with
                }
            }

            password?.let {
                val input = it.text.toString()
                if (input.isEmpty()) {
                    it.requestFocus()
                    it.setErrorOnEdit(LoginConstants.passwordError)
                    return@with
                } else if (input.length < 8) {
                    it.requestFocus()
                    it.setErrorOnEdit(LoginConstants.passwordLengthError)
                    return@with
                }
            }

            onLoginDataValid.invoke(email?.text.toString(), password?.text.toString())
        }


}
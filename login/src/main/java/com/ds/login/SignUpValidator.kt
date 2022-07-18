package com.ds.login

import android.util.Patterns
import com.ds.login.LoginConstants.emailError
import com.ds.login.LoginConstants.firstNameError
import com.ds.login.LoginConstants.invalidEmailError
import com.ds.login.LoginConstants.lastNameError
import com.ds.login.LoginConstants.passwordError
import com.ds.login.LoginConstants.passwordLengthError
import com.ds.login.LoginConstants.passwordsDoNotMatchError
import com.ds.login.LoginConstants.reEnterPasswordError


class SignUpValidator(private val provider: SignUpComponentsProvider) {

    fun validateSignUpData(onValidData: (firstName: String, lastName: String, email: String, password: String) -> Unit) =
        with(provider) {
            removeError(firstName, lastName, email, password, secondPassword)
            firstName?.let {
                val input = it.text.toString()
                if (input.isEmpty()) {
                    it.requestFocus()
                    it.setErrorOnEdit(firstNameError)
                    return
                }
            }
            lastName?.let {
                val input = it.text.toString()
                if (input.isEmpty()) {
                    it.requestFocus()
                    it.setErrorOnEdit(lastNameError)
                    return
                }
            }
            email?.let {
                val input = it.text.toString()
                if (input.isEmpty()) {
                    it.requestFocus()
                    it.setErrorOnEdit(emailError)
                    return
                } else if (!Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
                    it.requestFocus()
                    it.setErrorOnEdit(invalidEmailError)
                    return
                }
            }

            password?.let {
                val input = it.text.toString()
                if (input.isEmpty()) {
                    it.requestFocus()
                    it.setErrorOnEdit(passwordError)
                    return
                } else if (input.length < 8) {
                    it.requestFocus()
                    it.setErrorOnEdit(passwordLengthError)
                    return
                }
            }

            secondPassword?.let {
                val input = it.text.toString()
                if (input.isEmpty()) {
                    it.requestFocus()
                    it.setErrorOnEdit(reEnterPasswordError)
                    return
                }
            }

            if (password != null && secondPassword != null) {
                val passwordInput = password?.text.toString()
                val secondPasswordInput = secondPassword?.text.toString()
                if (passwordInput != secondPasswordInput) {
                    secondPassword?.requestFocus()
                    secondPassword?.setErrorOnEdit(passwordsDoNotMatchError)
                    return
                }
            }
            onValidData.invoke(
                firstName?.text.toString(),
                lastName?.text.toString(),
                email?.text.toString(),
                password?.text.toString()
            )
        }
}
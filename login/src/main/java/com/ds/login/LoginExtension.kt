package com.ds.login

import android.widget.EditText

fun EditText.setErrorOnEdit(errorText: String) {
    error = errorText
}

fun EditText.removeError() {
    error = null
}

fun removeError(vararg  inputs : EditText?){
    inputs.forEach {
        it?.removeError()
    }
}
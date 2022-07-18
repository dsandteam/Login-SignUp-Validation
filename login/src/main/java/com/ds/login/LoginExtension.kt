package com.ds.login

import android.widget.EditText
/**
 *
 * Created By Amir Fury on 18 July 2022
 *
 * **/
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
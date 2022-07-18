package com.ds.login

import android.widget.EditText

interface LoginComponentsProvider {

    val email: EditText?

    val password: EditText?
}
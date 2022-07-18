package com.ds.login

import android.widget.EditText

interface SignUpComponentsProvider : LoginComponentsProvider {

    val firstName: EditText?

    val lastName: EditText?

    val secondPassword: EditText?
}
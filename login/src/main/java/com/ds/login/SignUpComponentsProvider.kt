package com.ds.login

import android.widget.EditText
/**
 *
 * Created By Amir Fury on 18 July 2022
 *
 * **/
interface SignUpComponentsProvider : LoginComponentsProvider {

    val firstName: EditText?

    val lastName: EditText?

    val secondPassword: EditText?
}
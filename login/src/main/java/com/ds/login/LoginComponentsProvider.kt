package com.ds.login

import android.widget.EditText
/**
 *
 * Created By Amir Fury on 18 July 2022
 *
 * **/
interface LoginComponentsProvider {

    val email: EditText?

    val password: EditText?
}
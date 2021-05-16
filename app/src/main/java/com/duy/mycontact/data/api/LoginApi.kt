package com.duy.mycontact.data.api

import com.duy.mycontact.data.login.model.LoggedInUser

interface LoginApi {
    suspend fun login(userName: String, password: String): LoggedInUser
}
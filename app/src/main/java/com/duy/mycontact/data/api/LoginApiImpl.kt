package com.duy.mycontact.data.api

import com.duy.mycontact.data.login.model.LoggedInUser
import kotlinx.coroutines.delay

class LoginApiImpl : LoginApi {
    override suspend fun login(userName: String, password: String): LoggedInUser {
        // fake api to login
        delay(1000)
        if (userName == "devblock" && password == "2021") {
            return LoggedInUser("1", userName,"Dev Block")
        } else {
            throw Exception("User name or password is wrong", LoginException())
        }

    }
}

class LoginException : Throwable() {}
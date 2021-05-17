package com.duy.mycontact.data.login

import android.util.Log
import com.duy.mycontact.data.api.LoginApi
import com.duy.mycontact.data.base.Result
import com.duy.mycontact.data.login.model.LoggedInUser
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource(private val loginApi: LoginApi) {

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            val fakeUser =  loginApi.login(username, password)
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            Log.e("LoginDataSource", "login: >>> ${e.message}")
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}
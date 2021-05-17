package com.duy.mycontact.data.login

import com.duy.mycontact.data.base.Result
import com.duy.mycontact.data.login.model.LoggedInUser
import com.duy.mycontact.domain.LoginRepository

class LoginRepositoryImpl(private val dataSource: LoginDataSource) : LoginRepository {
    override suspend fun login(username: String, password: String): Result<LoggedInUser> {
        // handle login
        val result = dataSource.login(username, password)

        if (result is Result.Success) {
            saveLoggedInUser(result.data)
        }
        return result
    }

    private fun saveLoggedInUser(loggedInUser: LoggedInUser) {
        // save user credentials into SharedPreference for auto Login later
        // handle save user here
        //
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
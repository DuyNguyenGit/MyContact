package com.duy.mycontact.domain

import com.duy.mycontact.data.login.Result
import com.duy.mycontact.data.login.model.LoggedInUser

interface LoginRepository {

    suspend fun login(username: String, password: String): Result<LoggedInUser>
}
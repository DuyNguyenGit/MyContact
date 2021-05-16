package com.duy.mycontact.data.api

import com.duy.mycontact.data.contact_list.ContactListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ContactApi {

    @GET("api/users")
    suspend fun getContactList(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Response<ContactListResponse>
}
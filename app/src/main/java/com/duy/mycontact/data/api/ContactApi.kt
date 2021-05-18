package com.duy.mycontact.data.api

import com.duy.mycontact.data.contact_info.ContactInfoResponse
import com.duy.mycontact.data.contact_info.ContactUpdateData
import com.duy.mycontact.data.contact_list.ContactListResponse
import retrofit2.Response
import retrofit2.http.*

interface ContactApi {

    @GET("api/users")
    suspend fun getContactList(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Response<ContactListResponse>

    @GET("api/users/{contactId}")
    suspend fun getContactInfo(@Path("contactId") contactId: String): Response<ContactInfoResponse>

    @PUT("api/users/{contactId}")
    suspend fun updateContactInfo(@Path("contactId") contactId: String,
                            @Body data: ContactUpdateData
    ): Response<ContactUpdateData>
}
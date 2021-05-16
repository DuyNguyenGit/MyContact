package com.duy.mycontact.data

import com.duy.mycontact.data.api.ContactApi
import com.duy.mycontact.data.base.Resource
import com.duy.mycontact.data.contact_list.ContactListResponse
import com.duy.mycontact.domain.ContactListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactListRepositoryImpl(private val contactApi: ContactApi) : ContactListRepository {
    override suspend fun getContactList(page: Int, pageSize: Int): Resource<ContactListResponse> =
        withContext(Dispatchers.IO) {
            try {
                val response = contactApi.getContactList(page, pageSize)
                if (response.isSuccessful) {
                    Resource.success(response.body())
                } else {
                    Resource.error(response.message())
                }
            } catch (ex: Throwable) {
                Resource.error<ContactListResponse>("${ex.message}")
            }
        }
}
package com.duy.mycontact.data.contact_info

import com.duy.mycontact.data.api.ContactApi
import com.duy.mycontact.data.base.Result
import com.duy.mycontact.data.common.Contact
import com.duy.mycontact.domain.ContactDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class ContactDetailRepositoryImpl(private val contactApi: ContactApi) : ContactDetailRepository {
    override suspend fun getContactInfo(contactId: Int): Result<Contact> {
        var result: Result<Contact>?
        return withContext(Dispatchers.IO) {
            try {
                val response = contactApi.getContactInfo(contactId.toString())
                if (response.isSuccessful) {
                    result = Result.Success((response.body() as ContactInfoResponse).contactInfo)
                } else {
                    result = Result.Error(IOException(response.message()))
                }
            } catch (ex: Throwable) {
                result = Result.Error(IOException(ex.message))
            }
            result!!
        }

    }
}
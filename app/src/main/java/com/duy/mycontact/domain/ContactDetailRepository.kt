package com.duy.mycontact.domain

import com.duy.mycontact.data.base.Result
import com.duy.mycontact.data.common.Contact
import com.duy.mycontact.data.contact_info.ContactUpdateData

interface ContactDetailRepository {

    suspend fun getContactInfo(contactId: Int): Result<Contact>

    suspend fun updateContactInfo(contactId: Int, data: ContactUpdateData): Result<ContactUpdateData>
}
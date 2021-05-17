package com.duy.mycontact.domain

import com.duy.mycontact.data.common.Contact
import com.duy.mycontact.data.base.Result

interface ContactDetailRepository {

    suspend fun getContactInfo(contactId: Int): Result<Contact>
}
package com.duy.mycontact.domain

import com.duy.mycontact.data.base.Resource
import com.duy.mycontact.data.common.Contact

interface ContactDetailRepository {

    suspend fun getContactInfo(contactId: Int): Resource<Contact>
}
package com.duy.mycontact.data

import com.duy.mycontact.api.ContactApi
import com.duy.mycontact.domain.ContactListRepository

class ContactListRepositoryImpl(private val contactApi: ContactApi) : ContactListRepository {
}
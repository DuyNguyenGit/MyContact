package com.duy.mycontact.data

import com.duy.mycontact.data.api.ContactApi
import com.duy.mycontact.domain.ContactDetailRepository

class ContactDetailRepositoryImpl(private val contactApi: ContactApi) : ContactDetailRepository {
}
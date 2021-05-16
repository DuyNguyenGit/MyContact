package com.duy.mycontact.domain

import com.duy.mycontact.data.base.Resource
import com.duy.mycontact.data.contact_list.ContactListResponse

interface ContactListRepository {

    suspend fun getContactList(page: Int, pageSize: Int): Resource<ContactListResponse>
}
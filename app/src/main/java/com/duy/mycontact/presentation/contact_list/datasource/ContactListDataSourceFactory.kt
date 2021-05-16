package com.duy.mycontact.presentation.contact_list.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.duy.mycontact.data.contact_list.Contact
import com.duy.mycontact.domain.ContactListRepository

class ContactListDataSourceFactory(private val contactListRepository: ContactListRepository) :
    DataSource.Factory<Int, Contact>() {
    val source: MutableLiveData<ContactListDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, Contact> {
        val contactListDataSource = ContactListDataSource(contactListRepository)
        source.postValue(contactListDataSource)
        return contactListDataSource
    }

}
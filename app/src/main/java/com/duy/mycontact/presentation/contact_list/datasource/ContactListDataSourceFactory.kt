package com.duy.mycontact.presentation.contact_list.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.duy.mycontact.data.common.Contact
import com.duy.mycontact.domain.ContactListRepository

class ContactListDataSourceFactory(private val contactListRepository: ContactListRepository) :
    DataSource.Factory<Int, Contact>() {
    private var query: String = ""
    val source: MutableLiveData<ContactListDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, Contact> {
        val contactListDataSource = ContactListDataSource(contactListRepository, query)
        source.postValue(contactListDataSource)
        return contactListDataSource
    }

    fun getSource() = source.value

    fun updateQuery(query: String){
        this.query = query
        getSource()?.refresh()
    }

}
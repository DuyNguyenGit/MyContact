package com.duy.mycontact.presentation.contact_list.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.duy.mycontact.data.base.Status
import com.duy.mycontact.data.common.Contact
import com.duy.mycontact.domain.ContactListRepository
import kotlinx.coroutines.*

class ContactListDataSource(
    private val contactListRepository: ContactListRepository,
    private val query: String
) :
    PageKeyedDataSource<Int, Contact>() {

    private val dataSourceJob = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main + dataSourceJob)
    val loadStateLiveData: MutableLiveData<Status> = MutableLiveData()

    companion object {
        const val PAGE_SIZE = 15
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Contact>
    ) {
        scope.launch {
            loadStateLiveData.postValue(Status.LOADING)

            val response = contactListRepository.getContactList(1, PAGE_SIZE)
            when (response.status) {
                Status.ERROR -> loadStateLiveData.postValue(Status.ERROR)
                Status.EMPTY -> loadStateLiveData.postValue(Status.EMPTY)
                else -> {
                    response.data?.let {
                        callback.onResult(it.contacts.filterByQuery(query), null, 2)
                        loadStateLiveData.postValue(Status.SUCCESS)
                    }
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Contact>) {
        scope.launch {
            val response = contactListRepository.getContactList(params.key, PAGE_SIZE)
            response.data?.let {
                callback.onResult(it.contacts.filterByQuery(query), params.key + 1)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Contact>) {

    }

    private fun List<Contact>.filterByQuery(query: String): List<Contact> {
        if (query.isNotEmpty()) {
            return filter {
                query == it.email
            }
        }
        return this
    }

    fun refresh() = this.invalidate()

    override fun invalidate() {
        super.invalidate()
        dataSourceJob.cancelChildren()   // Cancel possible running job to only keep last result searched by user
    }
}
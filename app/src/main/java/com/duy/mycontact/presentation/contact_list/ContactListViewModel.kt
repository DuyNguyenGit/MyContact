package com.duy.mycontact.presentation.contact_list

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.duy.mycontact.R
import com.duy.mycontact.data.base.Status
import com.duy.mycontact.data.common.Contact
import com.duy.mycontact.presentation.contact_list.datasource.ContactListDataSource
import com.duy.mycontact.presentation.contact_list.datasource.ContactListDataSourceFactory
import java.util.concurrent.Executors

class ContactListViewModel(private val contactListDataSourceFactory: ContactListDataSourceFactory) :
    ViewModel() {
    private val TAG = ContactListViewModel::class.java.simpleName
    lateinit var contactListLiveData: LiveData<PagedList<Contact>>
    var dataSource: MutableLiveData<ContactListDataSource>
    private val _isWaiting: MutableLiveData<Boolean> = MutableLiveData()
    val isWaiting: LiveData<Boolean> = _isWaiting
    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    init {
        _isWaiting.value = true
        errorMessage.value = null
        dataSource = contactListDataSourceFactory.source
        initUsersListFactory()
    }

    private fun initUsersListFactory() {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(ContactListDataSource.PAGE_SIZE)
            .setPageSize(ContactListDataSource.PAGE_SIZE)
            .setPrefetchDistance(3)
            .build()

        val executor = Executors.newFixedThreadPool(5)

        contactListLiveData =
            LivePagedListBuilder<Int, Contact>(contactListDataSourceFactory, config)
                .setFetchExecutor(executor)
                .build()
    }

    fun setUpObservers(viewLifecycleOwner: LifecycleOwner) {
        Transformations.switchMap(dataSource) { dataSource -> dataSource.loadStateLiveData }
            .observe(viewLifecycleOwner, Observer {
                when (it) {
                    Status.LOADING -> {
                        _isWaiting.value = true
                        errorMessage.value = null
                    }
                    Status.SUCCESS -> {
                        _isWaiting.value = false
                        errorMessage.value = null
                    }
                    Status.EMPTY -> {
                        _isWaiting.value = false
                        errorMessage.value = R.string.msg_contact_list_is_empty
                    }
                    else -> {
                        _isWaiting.value = false
                        errorMessage.value = R.string.msg_fetch_contact_list_has_error
                    }
                }
            })
    }
}
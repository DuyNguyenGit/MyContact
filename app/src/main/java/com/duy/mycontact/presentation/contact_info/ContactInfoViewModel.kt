package com.duy.mycontact.presentation.contact_info

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duy.mycontact.data.base.Status
import com.duy.mycontact.data.common.Contact
import com.duy.mycontact.domain.ContactDetailRepository
import kotlinx.coroutines.launch

class ContactInfoViewModel(private val contactDetailRepository: ContactDetailRepository) :
    ViewModel() {

    val isWaiting: ObservableField<Boolean> = ObservableField()
    val errorMessage: ObservableField<String> = ObservableField()
    val contactModel: ObservableField<Contact> = ObservableField()

    init {
        isWaiting.set(true)
        errorMessage.set(null)
    }

    fun getContactInfo(contactId: Int) {
        viewModelScope.launch {
            val result = contactDetailRepository.getContactInfo(contactId)
            if (result.status == Status.SUCCESS) {
                contactModel.set(result.data)
                errorMessage.set(null)

            } else {
                contactModel.set(null)
                errorMessage.set(result.message)
            }

            isWaiting.set(false)
        }
    }

    fun updateContactInfo() {
        viewModelScope.launch {

        }
    }
}
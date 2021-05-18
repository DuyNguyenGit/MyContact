package com.duy.mycontact.presentation.contact_info

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duy.mycontact.data.base.Result
import com.duy.mycontact.data.common.Contact
import com.duy.mycontact.data.contact_info.ContactUpdateData
import com.duy.mycontact.domain.ContactDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactInfoViewModel(private val contactDetailRepository: ContactDetailRepository) :
    ViewModel() {

    private val TAG: String = ContactInfoViewModel::class.java.simpleName
    private val _isWaiting: MutableLiveData<Boolean> = MutableLiveData()
    val isWaiting: LiveData<Boolean> get() = _isWaiting
    private val _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> get() = _errorMessage
    private val _contactModel: MutableLiveData<Contact> = MutableLiveData()
    val contactModel: LiveData<Contact> get() = _contactModel
    private val _contactUpdateModel: MutableLiveData<ContactUpdateData> = MutableLiveData()
    val contactUpdateModel: LiveData<ContactUpdateData> get() = _contactUpdateModel

    init {
        _isWaiting.value = true
    }

    fun getContactInfo(contactId: Int) {
        Log.d(TAG, "getContactInfo: >>Entry")
        viewModelScope.launch {
            val result = contactDetailRepository.getContactInfo(contactId)
            if (result is Result.Success) {
                Log.d(TAG, "getContactInfo: >>>SUCCESS")
                _contactModel.value = result.data
                _errorMessage.value = null
            } else {
                Log.d(TAG, "getContactInfo: >>>ERROR: ${(result as Result.Error).exception.localizedMessage}")
                _contactModel.value = null
                _errorMessage.value = result.exception.localizedMessage
            }

            Log.d(TAG, "getContactInfo: >>END")
            _isWaiting.value = false
        }
    }

    fun updateContactInfo(contactId: Int?, name: String, email: String) {
        _isWaiting.value = true
        viewModelScope.launch {
            contactId?.let { contactId ->
                val result = contactDetailRepository.updateContactInfo(contactId, ContactUpdateData(name, email))
                if (result is Result.Success) {
                    Log.d(TAG, "getContactInfo: >>>SUCCESS")
                    _contactUpdateModel.value = result.data
                    _errorMessage.value = null
                } else {
                    Log.d(TAG, "getContactInfo: >>>ERROR: ${(result as Result.Error).exception.localizedMessage}")
                    _contactUpdateModel.value = null
                    _errorMessage.value = result.exception.localizedMessage
                }

                Log.d(TAG, "getContactInfo: >>END")
                _isWaiting.value = false
            }
        }
    }
}
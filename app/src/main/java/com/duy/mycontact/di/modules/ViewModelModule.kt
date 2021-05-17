package com.duy.mycontact.di.modules

import com.duy.mycontact.presentation.contact_info.ContactInfoViewModel
import com.duy.mycontact.presentation.contact_list.ContactListViewModel
import com.duy.mycontact.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { ContactListViewModel(get()) }
    viewModel { ContactInfoViewModel(get()) }
}
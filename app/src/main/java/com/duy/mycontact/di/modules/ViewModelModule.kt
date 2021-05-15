package com.duy.mycontact.di.modules

import com.duy.mycontact.presentation.login.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
}
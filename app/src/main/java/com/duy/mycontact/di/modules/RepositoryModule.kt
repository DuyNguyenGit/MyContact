package com.duy.mycontact.di.modules

import com.duy.mycontact.data.ContactDetailRepositoryImpl
import com.duy.mycontact.data.ContactListRepositoryImpl
import com.duy.mycontact.data.login.LoginDataSource
import com.duy.mycontact.data.login.LoginRepositoryImpl
import com.duy.mycontact.domain.ContactDetailRepository
import com.duy.mycontact.domain.ContactListRepository
import com.duy.mycontact.domain.LoginRepository
import org.koin.dsl.module


val repositoryModule = module {
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single<ContactListRepository> { ContactListRepositoryImpl(get()) }
    single<ContactDetailRepository> { ContactDetailRepositoryImpl(get()) }
    factory { LoginDataSource(get()) }
}
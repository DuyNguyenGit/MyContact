package com.duy.mycontact

import android.app.Application
import com.duy.mycontact.di.modules.apiModule
import com.duy.mycontact.di.modules.repositoryModule
import com.duy.mycontact.di.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ContactApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ContactApp)
            modules(
                listOf(
                    viewModelModule,
                    apiModule,
                    repositoryModule
                )
            )
        }
    }
}
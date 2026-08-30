package com.example.textrepeaterapp

import android.app.Application
import com.example.textrepeaterapp.core.di.appModule
import com.example.textrepeaterapp.core.di.domainModule
import com.example.textrepeaterapp.core.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppClass : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppClass)
            modules(appModule, domainModule, presentationModule)
        }
    }
}
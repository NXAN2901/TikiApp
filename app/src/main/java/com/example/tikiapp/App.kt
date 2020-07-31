package com.example.tikiapp

import android.app.Application
import com.example.tikiapp.di.networkModule
import com.example.tikiapp.di.repositoryModule
import com.example.tikiapp.di.useCaseModule
import com.example.tikiapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    viewModelModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule
                )
            )
        }
    }
}
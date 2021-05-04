package com.highestaim.newsapp

import android.app.Application
import com.highestaim.newsapp.di.remoteModule
import com.highestaim.newsapp.di.repository
import com.highestaim.newsapp.di.appViewModels
import com.highestaim.newsapp.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NewsAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@NewsAppApplication)
            modules (listOf(
                remoteModule,
                appViewModels,
                repository,
                databaseModule
            )
            )

        }
    }
}
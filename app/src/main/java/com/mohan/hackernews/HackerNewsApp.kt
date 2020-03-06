package com.mohan.hackernews

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class HackerNewsApp : Application() {

    companion object {
        lateinit var instance: HackerNewsApp
        fun get(): HackerNewsApp = instance
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@HackerNewsApp)
            androidFileProperties()
            modules(appModule)
        }
        instance = this
    }
}
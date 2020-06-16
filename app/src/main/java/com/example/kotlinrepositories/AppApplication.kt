package com.example.kotlinrepositories

import android.app.Application
import com.example.kotlinrepositories.core.di.addModule
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())
        startKoin {
            androidContext(this@AppApplication)
            modules(addModule)
        }
    }
}
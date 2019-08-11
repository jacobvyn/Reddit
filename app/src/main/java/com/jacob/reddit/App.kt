package com.jacob.reddit

import android.app.Application
import androidx.databinding.DataBindingUtil
import com.jacob.reddit.databinding.component.DefaultDataBindingComponentImpl
import com.jacob.reddit.koin.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@App)
            modules(appModules)
        }

        instance = this
        DataBindingUtil.setDefaultComponent(DefaultDataBindingComponentImpl())
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        lateinit var instance: App
    }
}
package com.jacob.reddit

import android.app.Application
import androidx.databinding.DataBindingUtil
import com.jacob.reddit.databinding.component.DefaultDataBindingComponentImpl
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
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
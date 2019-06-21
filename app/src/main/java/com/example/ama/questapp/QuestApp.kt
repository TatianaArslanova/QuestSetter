package com.example.ama.questapp

import com.example.ama.questapp.di.DaggerAppComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class QuestApp : DaggerApplication() {

    companion object {
        lateinit var instance: QuestApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initStetho()
        initTimber()
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerAppComponent.builder()
                    .app(this)
                    .build()
}
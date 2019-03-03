package com.example.ama.questapp;

import com.example.ama.questapp.di.DaggerAppComponent;
import com.facebook.stetho.Stetho;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

import static timber.log.Timber.DebugTree;

public class QuestApp extends DaggerApplication {
    private static QuestApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initStetho();
        initTimber();
    }

    public static QuestApp getInstance() {
        return instance;
    }

    private void initStetho() {
        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this);
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(new DebugTree());
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .app(this)
                .build();
    }
}

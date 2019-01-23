package com.example.ama.questapp;

import android.app.Application;

import com.example.ama.questapp.di.AppComponent;
import com.example.ama.questapp.di.AppModule;
import com.example.ama.questapp.di.DaggerAppComponent;
import com.facebook.stetho.Stetho;

import timber.log.Timber;

import static timber.log.Timber.DebugTree;

public class QuestApp extends Application {
    private static QuestApp instance;
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initComponent();
    }

    private void initComponent() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        initStetho();
        initTimber();
    }

    public static QuestApp getInstance() {
        return instance;
    }

    public AppComponent getComponent() {
        return component;
    }

    private void initStetho() {
        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this);
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(new DebugTree());
    }
}

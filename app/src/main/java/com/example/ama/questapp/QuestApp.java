package com.example.ama.questapp;

import android.app.Application;

import com.example.ama.questapp.di.AppComponent;
import com.example.ama.questapp.di.AppModule;
import com.example.ama.questapp.di.DaggerAppComponent;

public class QuestApp extends Application {
    private static QuestApp instance;
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static QuestApp getInstance() {
        return instance;
    }

    public AppComponent getComponent() {
        return component;
    }
}

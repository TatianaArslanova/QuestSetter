package com.example.ama.questapp;

import android.app.Application;

import com.example.ama.questapp.di.AppComponent;
import com.example.ama.questapp.di.AppModule;
import com.example.ama.questapp.di.DaggerAppComponent;
import com.example.ama.questapp.repo.event.EventPlanner;

import javax.inject.Inject;

public class QuestApp extends Application {
    private static QuestApp instance;
    private AppComponent component;

    @Inject
    EventPlanner planner;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initComponent();
        planner.setAutoProduceDailyQuests();
    }

    private void initComponent() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        component.inject(this);
    }

    public static QuestApp getInstance() {
        return instance;
    }

    public AppComponent getComponent() {
        return component;
    }
}

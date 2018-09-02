package com.example.ama.questapp;

import android.app.Application;

public class QuestApp extends Application {
    private static QuestApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static QuestApp getInstance() {
        return instance;
    }
}

package com.example.ama.questapp.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Context app;

    public AppModule(Context app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideApp() {
        return app;
    }
}
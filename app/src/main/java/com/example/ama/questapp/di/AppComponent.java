package com.example.ama.questapp.di;

import com.example.ama.questapp.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DbModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}

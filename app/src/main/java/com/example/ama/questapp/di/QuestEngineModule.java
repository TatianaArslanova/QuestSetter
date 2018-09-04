package com.example.ama.questapp.di;

import com.example.ama.questapp.repo.engine.UserQuestEngine;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class QuestEngineModule {

    @Provides
    @Singleton
    UserQuestEngine provideUserQuestEngine() {
        return new UserQuestEngine();
    }
}

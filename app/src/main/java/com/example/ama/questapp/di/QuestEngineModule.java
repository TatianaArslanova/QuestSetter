package com.example.ama.questapp.di;

import com.example.ama.questapp.engine.UserQuestEngine;
import com.example.ama.questapp.engine.operations.UserQuestCreator;
import com.example.ama.questapp.engine.operations.UserQuestUpdater;
import com.example.ama.questapp.engine.operations.provider.EngineQuestProvider;
import com.example.ama.questapp.engine.producer.DailyUserTaskProducer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class QuestEngineModule {

    @Provides
    @Singleton
    UserQuestEngine provideUserQuestEngine(DailyUserTaskProducer producer,
                                           UserQuestCreator creator,
                                           UserQuestUpdater updater) {
        return new UserQuestEngine(producer, creator, updater);
    }

    @Provides
    @Singleton
    DailyUserTaskProducer provideUserTaskProducer(EngineQuestProvider questProvider) {
        return new DailyUserTaskProducer(questProvider);
    }

    @Provides
    @Singleton
    UserQuestCreator provideUserQuestCreator(EngineQuestProvider questProvider) {
        return new UserQuestCreator(questProvider);
    }

    @Provides
    @Singleton
    UserQuestUpdater provideUserQuestUpdater(EngineQuestProvider questProvider) {
        return new UserQuestUpdater(questProvider);
    }
}

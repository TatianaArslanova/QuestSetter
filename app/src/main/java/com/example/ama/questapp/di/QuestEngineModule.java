package com.example.ama.questapp.di;

import com.example.ama.questapp.data.repo.provider.EngineQuestProvider;
import com.example.ama.questapp.domain.engine.UserQuestEngine;
import com.example.ama.questapp.domain.engine.operations.UserTaskCreator;
import com.example.ama.questapp.domain.engine.operations.UserTaskUpdater;
import com.example.ama.questapp.domain.engine.producer.DailyUserTaskProducer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class QuestEngineModule {

    @Provides
    @Singleton
    UserQuestEngine provideUserQuestEngine(DailyUserTaskProducer producer,
                                           UserTaskCreator creator,
                                           UserTaskUpdater updater) {
        return new UserQuestEngine(producer, creator, updater);
    }

    @Provides
    @Singleton
    DailyUserTaskProducer provideUserTaskProducer(EngineQuestProvider questProvider) {
        return new DailyUserTaskProducer(questProvider);
    }

    @Provides
    @Singleton
    UserTaskCreator provideUserTaskCreator(EngineQuestProvider questProvider) {
        return new UserTaskCreator(questProvider);
    }

    @Provides
    @Singleton
    UserTaskUpdater provideUserTaskUpdater(EngineQuestProvider questProvider) {
        return new UserTaskUpdater(questProvider);
    }
}

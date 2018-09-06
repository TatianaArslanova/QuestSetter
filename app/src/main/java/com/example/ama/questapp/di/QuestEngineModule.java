package com.example.ama.questapp.di;

import com.example.ama.questapp.engine.UserQuestEngine;
import com.example.ama.questapp.engine.operations.UserQuestCreator;
import com.example.ama.questapp.engine.operations.UserQuestUpdater;
import com.example.ama.questapp.engine.producer.DailyUserTaskProducer;
import com.example.ama.questapp.repo.db.QuestDatabase;

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
    DailyUserTaskProducer provideUserTaskProducer(QuestDatabase database) {
        return new DailyUserTaskProducer(database);
    }

    @Provides
    @Singleton
    UserQuestCreator provideUserQuestCreator(QuestDatabase database) {
        return new UserQuestCreator(database);
    }

    @Provides
    @Singleton
    UserQuestUpdater provideUserQuestUpdater(QuestDatabase database) {
        return new UserQuestUpdater(database);
    }
}

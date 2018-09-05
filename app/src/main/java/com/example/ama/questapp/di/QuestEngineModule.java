package com.example.ama.questapp.di;

import com.example.ama.questapp.repo.db.QuestDatabase;
import com.example.ama.questapp.repo.engine.UserQuestEngine;
import com.example.ama.questapp.repo.engine.producer.DailyUserTaskProducer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class QuestEngineModule {

    @Provides
    @Singleton
    UserQuestEngine provideUserQuestEngine(DailyUserTaskProducer producer) {
        return new UserQuestEngine(producer);
    }

    @Provides
    @Singleton
    DailyUserTaskProducer provideUserTaskProducer(QuestDatabase database) {
        return new DailyUserTaskProducer(database);
    }
}

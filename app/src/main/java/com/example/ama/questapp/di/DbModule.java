package com.example.ama.questapp.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.ama.questapp.data.repo.provider.EngineQuestProvider;
import com.example.ama.questapp.data.repo.provider.EngineQuestProviderImpl;
import com.example.ama.questapp.data.db.QuestDatabase;
import com.example.ama.questapp.data.repo.provider.QuestDatabaseProvider;
import com.example.ama.questapp.data.repo.provider.QuestProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {
    private static final String DB_NAME = "questApp";

    @Provides
    @Singleton
    QuestDatabase provideQuestDatabase(Context context) {
        return Room.databaseBuilder(context, QuestDatabase.class, DB_NAME).build();
    }

    @Provides
    @Singleton
    QuestProvider provideQuestProvider(QuestDatabase database) {
        return new QuestDatabaseProvider(database);
    }

    @Provides
    @Singleton
    EngineQuestProvider provideEngineQuestProvider(QuestDatabase database) {
        return new EngineQuestProviderImpl(database);
    }
}

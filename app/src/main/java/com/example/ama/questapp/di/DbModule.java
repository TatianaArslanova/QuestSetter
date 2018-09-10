package com.example.ama.questapp.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.ama.questapp.data.db.QuestDatabase;
import com.example.ama.questapp.data.repo.provider.EngineQuestProvider;
import com.example.ama.questapp.data.repo.provider.EngineQuestProviderImpl;
import com.example.ama.questapp.data.repo.provider.QuestDatabaseProvider;
import com.example.ama.questapp.data.repo.provider.QuestProvider;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class DbModule {
    private static final String DB_NAME = "questApp";

    @Provides
    @Singleton
    static QuestDatabase provideQuestDatabase(Context context) {
        return Room.databaseBuilder(context, QuestDatabase.class, DB_NAME).build();
    }

    @Binds
    @Singleton
    abstract QuestProvider provideQuestProvider(QuestDatabaseProvider questDatabaseProvider);

    @Binds
    @Singleton
    abstract EngineQuestProvider provideEngineQuestProvider(EngineQuestProviderImpl engineQuestProvider);
}

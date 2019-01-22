package com.example.ama.questapp.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.ama.questapp.data.db.QuestDatabase;
import com.example.ama.questapp.data.db.dao.QuestPatternDao;
import com.example.ama.questapp.data.db.dao.UserTaskDao;
import com.example.ama.questapp.data.db.dao.UserTaskWithPatternDao;
import com.example.ama.questapp.data.repo.provider.QuestPatternsProvider;
import com.example.ama.questapp.data.repo.provider.QuestPatternsProviderImpl;
import com.example.ama.questapp.data.repo.provider.UserTaskProvider;
import com.example.ama.questapp.data.repo.provider.UserTaskProviderImpl;

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

    @Provides
    @Singleton
    static QuestPatternDao provideQuestPatternDao(QuestDatabase database) {
        return database.getQuestPatternDao();
    }

    @Provides
    @Singleton
    static UserTaskDao provideUserTaskDao(QuestDatabase database) {
        return database.getUserTaskDao();
    }

    @Provides
    @Singleton
    static UserTaskWithPatternDao provadeUserTaskWithPatternDao(QuestDatabase database) {
        return database.getUserTaskWithPatternDao();
    }

    @Binds
    @Singleton
    abstract UserTaskProvider provideUserTaskProvider(UserTaskProviderImpl questDatabaseProvider);

    @Binds
    @Singleton
    abstract QuestPatternsProvider provideQuestPatternsProvider(QuestPatternsProviderImpl engineQuestProvider);
}

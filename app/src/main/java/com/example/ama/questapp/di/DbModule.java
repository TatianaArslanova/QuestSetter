package com.example.ama.questapp.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.ama.questapp.repo.db.QuestDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {
    private static final String dbName = "questApp";

    @Provides
    @Singleton
    QuestDatabase provideQuestDatabase(Context context) {
        return Room.databaseBuilder(context, QuestDatabase.class, dbName).build();
    }
}
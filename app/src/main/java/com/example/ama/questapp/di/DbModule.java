package com.example.ama.questapp.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.ama.questapp.repo.db.QuestDatabase;
import com.example.ama.questapp.repo.engine.DatabaseOperator;

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
    DatabaseOperator provideDatabaseOperator(QuestDatabase database){
        return new DatabaseOperator(database);
    }
}

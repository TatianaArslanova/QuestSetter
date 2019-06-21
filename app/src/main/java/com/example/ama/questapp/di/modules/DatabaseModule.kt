package com.example.ama.questapp.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.example.ama.questapp.data.db.QuestDatabase
import com.example.ama.questapp.data.db.dao.QuestPatternDao
import com.example.ama.questapp.data.db.dao.UserTaskDao
import com.example.ama.questapp.data.db.dao.UserTaskWithPatternDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    companion object {
        private const val DB_NAME = "questApp"
    }

    @Provides
    @Singleton
    fun provideQuestDatabase(context: Context): QuestDatabase =
            Room.databaseBuilder(context, QuestDatabase::class.java, DB_NAME).build()

    @Provides
    @Singleton
    fun provideQuestPatternDao(database: QuestDatabase): QuestPatternDao =
            database.questPatternDao()

    @Provides
    @Singleton
    fun provideUserTaskDao(database: QuestDatabase): UserTaskDao =
            database.userTaskDao()

    @Provides
    @Singleton
    fun provideUserTaskWithPatternDao(database: QuestDatabase): UserTaskWithPatternDao =
            database.userTaskWithPatternDao()
}
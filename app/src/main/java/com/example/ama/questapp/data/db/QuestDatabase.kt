package com.example.ama.questapp.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.ama.questapp.data.db.dao.QuestPatternDao
import com.example.ama.questapp.data.db.dao.UserTaskDao
import com.example.ama.questapp.data.db.dao.UserTaskWithPatternDao
import com.example.ama.questapp.data.db.model.Converter
import com.example.ama.questapp.data.db.model.QuestPattern
import com.example.ama.questapp.data.db.model.UserTask

@Database(entities = [QuestPattern::class, UserTask::class], version = 1)
@TypeConverters(Converter::class)
abstract class QuestDatabase : RoomDatabase() {

    abstract fun questPatternDao(): QuestPatternDao
    abstract fun userTaskDao(): UserTaskDao
    abstract fun userTaskWithPatternDao(): UserTaskWithPatternDao
}
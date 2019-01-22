package com.example.ama.questapp.data.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.ama.questapp.data.db.dao.QuestPatternDao;
import com.example.ama.questapp.data.db.dao.UserTaskDao;
import com.example.ama.questapp.data.db.dao.UserTaskWithPatternDao;
import com.example.ama.questapp.data.db.model.QuestPattern;
import com.example.ama.questapp.data.db.model.UserTask;

@Database(entities = {QuestPattern.class, UserTask.class}, version = 1)
public abstract class QuestDatabase extends RoomDatabase {
    public abstract QuestPatternDao getQuestPatternDao();

    public abstract UserTaskDao getUserTaskDao();

    public abstract UserTaskWithPatternDao getUserTaskWithPatternDao();
}

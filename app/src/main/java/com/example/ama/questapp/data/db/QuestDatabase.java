package com.example.ama.questapp.data.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.ama.questapp.data.db.dao.PatternWithStatusesDao;
import com.example.ama.questapp.data.db.dao.QuestDao;
import com.example.ama.questapp.data.db.dao.UserQuestDao;
import com.example.ama.questapp.data.db.model.QuestPattern;
import com.example.ama.questapp.data.db.model.UserTask;

@Database(entities = {QuestPattern.class, UserTask.class}, version = 1)
public abstract class QuestDatabase extends RoomDatabase {
    public abstract QuestDao getQuestDao();

    public abstract UserQuestDao getUserQuestDao();

    public abstract PatternWithStatusesDao getPatternWithStatusesDao();
}

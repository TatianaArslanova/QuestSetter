package com.example.ama.questapp.repo.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.ama.questapp.repo.db.dao.PatternWithStatusesDao;
import com.example.ama.questapp.repo.db.dao.QuestDao;
import com.example.ama.questapp.repo.db.dao.UserTaskDao;
import com.example.ama.questapp.repo.model.QuestPattern;
import com.example.ama.questapp.repo.model.UserTask;

@Database(entities = {QuestPattern.class, UserTask.class}, version = 4)
public abstract class QuestDatabase extends RoomDatabase {
    public abstract QuestDao getQuestDao();

    public abstract UserTaskDao getStatusDao();

    public abstract PatternWithStatusesDao getPatternWithStatusesDao();
}

package com.example.ama.questapp.data.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.ama.questapp.data.db.dao.PatternWithStatusesDao;
import com.example.ama.questapp.data.db.dao.QuestDao;
import com.example.ama.questapp.data.db.dao.QuestGlobalStatusDao;
import com.example.ama.questapp.data.db.dao.UserQuestDao;
import com.example.ama.questapp.data.db.model.GlobalStatus;
import com.example.ama.questapp.data.db.model.QuestPattern;
import com.example.ama.questapp.data.db.model.UserQuest;

@Database(entities = {QuestPattern.class, UserQuest.class, GlobalStatus.class}, version = 4)
public abstract class QuestDatabase extends RoomDatabase {
    public abstract QuestDao getQuestDao();

    public abstract UserQuestDao getUserQuestDao();

    public abstract PatternWithStatusesDao getPatternWithStatusesDao();

    public abstract QuestGlobalStatusDao getQuestGlobalStatusDao();
}

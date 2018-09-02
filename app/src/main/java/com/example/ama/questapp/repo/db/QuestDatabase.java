package com.example.ama.questapp.repo.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.ama.questapp.repo.model.QuestPattern;
import com.example.ama.questapp.repo.model.QuestStatus;

@Database(entities = {QuestPattern.class, QuestStatus.class}, version = 2)
public abstract class QuestDatabase extends RoomDatabase {
    public abstract QuestDao getQuestDao();
}

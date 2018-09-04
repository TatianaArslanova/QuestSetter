package com.example.ama.questapp.repo.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.example.ama.questapp.repo.model.GlobalStatus;

import java.util.List;

@Dao
public interface QuestGlobalStatusDao {

    @Insert
    void insertAllGlobalStatuses(List<GlobalStatus> globalStatuses);

    @Insert
    void insertGlobalStatus(GlobalStatus globalStatus);
}

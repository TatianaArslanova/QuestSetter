package com.example.ama.questapp.repo.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.ama.questapp.repo.model.GlobalStatus;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface QuestGlobalStatusDao {

    @Insert
    void insertAllGlobalStatuses(List<GlobalStatus> globalStatuses);

    @Insert
    void insertGlobalStatus(GlobalStatus globalStatus);

    @Update
    void updateGlobalStatus(GlobalStatus globalStatus);

    @Query("SELECT * FROM globalstatuses WHERE patternId=:patternId")
    Single<GlobalStatus> getGlobalStatusByPatternId(int patternId);
}

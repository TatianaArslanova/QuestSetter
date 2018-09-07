package com.example.ama.questapp.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.ama.questapp.data.db.model.GlobalStatus;

import java.util.List;

@Dao
public interface QuestGlobalStatusDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllGlobalStatuses(List<GlobalStatus> globalStatuses);

    @Insert
    void insertGlobalStatus(GlobalStatus globalStatus);

    @Update
    void updateGlobalStatus(GlobalStatus globalStatus);

    @Query("SELECT * FROM globalstatuses WHERE patternId=:patternId")
    GlobalStatus getGlobalStatusByPatternId(int patternId);
}

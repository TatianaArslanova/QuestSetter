package com.example.ama.questapp.repo.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.ama.questapp.repo.model.UserQuest;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface UserQuestDao {

    @Insert
    void insertAllQuestStatuses(List<UserQuest> userQuests);

    @Insert
    void insertStatus(UserQuest status);

    @Update
    void updateStatus(UserQuest status);

    @Query("SELECT * FROM usertasks WHERE patternId = :patternId")
    List<UserQuest> getAllStatusesFromPatternId(int patternId);
}

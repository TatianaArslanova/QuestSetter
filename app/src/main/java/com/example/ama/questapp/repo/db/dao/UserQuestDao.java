package com.example.ama.questapp.repo.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.example.ama.questapp.repo.model.UserQuest;

import java.util.List;

@Dao
public interface UserQuestDao {

    @Insert
    void insertAllQuestStatuses(List<UserQuest> userQuests);

    @Insert
    void insertStatus(UserQuest status);

    @Update
    void updateStatus(UserQuest status);
}

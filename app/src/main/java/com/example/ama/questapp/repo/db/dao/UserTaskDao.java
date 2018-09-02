package com.example.ama.questapp.repo.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.example.ama.questapp.repo.model.UserTask;

import java.util.List;

@Dao
public interface UserTaskDao {

    @Insert
    void insertAllQuestStatuses(List<UserTask> userTasks);

    @Insert
    void insertStatus(UserTask status);

    @Update
    void updateStatus(UserTask status);
}

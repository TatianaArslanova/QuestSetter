package com.example.ama.questapp.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.ama.questapp.data.db.model.UserTask;

import java.util.List;

@Dao
public interface UserQuestDao {

    @Insert
    void insertAllUserTasks(List<UserTask> userTasks);

    @Insert
    void insertUserTask(UserTask userTask);

    @Update
    void updateUserTask(UserTask userTask);

    @Query("SELECT * FROM user_task WHERE patternId = :patternId")
    List<UserTask> getAllStatusesFromPatternId(int patternId);
}

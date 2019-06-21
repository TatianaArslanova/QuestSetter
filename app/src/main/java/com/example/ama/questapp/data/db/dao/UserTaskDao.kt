package com.example.ama.questapp.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.example.ama.questapp.data.db.model.UserTask
import io.reactivex.Single

@Dao
interface UserTaskDao {

    @Insert
    fun insertAllUserTasks(userTasks: List<UserTask>)

    @Insert
    fun insertUserTask(userTask: UserTask)

    @Update
    fun updateUserTask(userTask: UserTask)

    @Query("SELECT * FROM user_task WHERE patternId = :patternId")
    fun getAllStatusesFromPatternId(patternId: Int): Single<List<UserTask>>
}
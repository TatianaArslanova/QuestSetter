package com.example.ama.questapp.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern
import io.reactivex.Flowable

@Dao
interface UserTaskWithPatternDao {

    @Query("SELECT * FROM user_task, quest_pattern WHERE user_task.patternId=quest_pattern.questId ORDER BY user_task.isCompleted")
    fun getAllUserTasksWithPattern(): Flowable<List<UserTaskWithPattern>>

}
package com.example.ama.questapp.data.db.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user_task")
data class UserTask(
        @PrimaryKey(autoGenerate = true)
        val taskId: Long,
        val patternId: Long?,
        val isCompleted: Boolean?,
        val taskTarget: Long?,
        val taskProgress: Long?
)
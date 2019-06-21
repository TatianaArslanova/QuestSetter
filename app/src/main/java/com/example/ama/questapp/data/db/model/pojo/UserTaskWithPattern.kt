package com.example.ama.questapp.data.db.model.pojo

import android.arch.persistence.room.Embedded
import com.example.ama.questapp.data.db.model.QuestPattern
import com.example.ama.questapp.data.db.model.UserTask

data class UserTaskWithPattern(
        @Embedded
        val userTask: UserTask,
        @Embedded
        val pattern: QuestPattern
)
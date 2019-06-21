package com.example.ama.questapp.domain.repointerface

import com.example.ama.questapp.data.db.model.UserTask
import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface UserTaskRepository {

    fun addUserTasks(userTasks: List<UserTask>): Completable

    fun updateUserTask(userTask: UserTask): Completable

    fun getAllTasksByPatternId(patternId: Int): Single<List<UserTask>>

    fun getAllUserTasksWithPattern(): Flowable<List<UserTaskWithPattern>>
}
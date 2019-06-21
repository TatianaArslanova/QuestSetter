package com.example.ama.questapp.data.repo

import com.example.ama.questapp.data.db.dao.QuestPatternDao
import com.example.ama.questapp.data.db.dao.UserTaskDao
import com.example.ama.questapp.data.db.dao.UserTaskWithPatternDao
import com.example.ama.questapp.data.db.model.UserTask
import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern
import com.example.ama.questapp.domain.repointerface.UserTaskRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserTaskRepositotyImpl @Inject constructor(
        private val userTaskWithPatternDao: UserTaskWithPatternDao,
        private val userTaskDao: UserTaskDao,
        private val questPatternDao: QuestPatternDao
) : UserTaskRepository {

    override fun getAllUserTasksWithPattern(): Flowable<List<UserTaskWithPattern>> {
        return userTaskWithPatternDao.getAllUserTasksWithPattern()
                .subscribeOn(Schedulers.io())
    }

    override fun addUserTasks(userTasks: List<UserTask>): Completable {
        return Completable.fromAction { userTaskDao.insertAllUserTasks(userTasks) }
                .subscribeOn(Schedulers.io())
    }

    override fun updateUserTask(userTask: UserTask): Completable {
        return Completable.fromAction { userTaskDao.updateUserTask(userTask) }
                .subscribeOn(Schedulers.io())
    }

    override fun getAllTasksByPatternId(patternId: Int): Single<List<UserTask>> {
        return userTaskDao.getAllStatusesFromPatternId(patternId)
                .subscribeOn(Schedulers.io())
    }
}
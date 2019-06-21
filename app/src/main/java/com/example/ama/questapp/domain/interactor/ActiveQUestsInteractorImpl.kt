package com.example.ama.questapp.domain.interactor

import com.example.ama.questapp.data.db.model.UserTask
import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern
import com.example.ama.questapp.domain.engine.QuestEngineGateway
import com.example.ama.questapp.domain.repointerface.UserTaskRepository
import com.example.ama.questapp.presentation.interactor.ActiveQuestsInteractor
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActiveQUestsInteractorImpl @Inject constructor(
        private val userTaskRepository: UserTaskRepository,
        private val userQuestEngine: QuestEngineGateway
) : ActiveQuestsInteractor {

    override fun completeQuest(userTask: UserTask): Completable {
        return userQuestEngine.onIncrementQuestProgress(userTask)
    }

    override fun loadAllUserQuests(): Flowable<List<UserTaskWithPattern>> {
        userQuestEngine.addNewRandomDailyQuests(4).subscribe()
        return userTaskRepository.getAllUserTasksWithPattern()
    }
}
package com.example.ama.questapp.domain.engine

import com.example.ama.questapp.data.db.model.QuestPattern
import com.example.ama.questapp.data.db.model.QuestType
import com.example.ama.questapp.data.db.model.UserTask
import com.example.ama.questapp.domain.repointerface.QuestPatternsRepository
import com.example.ama.questapp.domain.repointerface.UserTaskRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestEngine @Inject constructor(
        private val questPatternsRepository: QuestPatternsRepository,
        private val userTaskRepository: UserTaskRepository
) : QuestEngineGateway {

    companion object {
        private const val PROGRESS_INCREMENT = 1
        private const val AUTO_ID = 0L
        private const val START_PROGRESS = 0

        private const val ONCE_DEFAULT_TARGET = 1
        private const val COUNT_DEFAULT_TARGET = 3
    }

    private val random = Random()

    override fun onIncrementQuestProgress(userTask: UserTask): Completable {
        return userTaskRepository.updateUserTask(incrementProgress(userTask))
    }

    override fun addNewRandomDailyQuests(maxCount: Int): Completable {
        return getQuestPatternsForUserTasks(maxCount)
                .flatMapObservable { Observable.fromIterable(it) }
                .map { this.createUserTaskFromPattern(it) }
                .buffer(maxCount)
                .flatMapCompletable { userTasks -> userTaskRepository.addUserTasks(userTasks) }
    }

    private fun incrementProgress(userTask: UserTask): UserTask {
        val updatedUserTask = userTask.copy(taskProgress = (userTask.taskProgress
                ?: 0) + PROGRESS_INCREMENT)
        return if (userTask.taskTarget === userTask.taskProgress) {
            updatedUserTask.copy(isCompleted = true)
        } else {
            updatedUserTask
        }
    }

    private fun getQuestPatternsForUserTasks(maxCount: Int): Single<List<QuestPattern>> {
        return questPatternsRepository.getUnusedDailyQuestPatterns()
                .map { questPatterns -> selectPatterns(questPatterns, maxCount) }
                .flatMap { questPatterns ->
                    if (questPatterns.size == maxCount)
                        Single.just(questPatterns)
                    else
                        addRepeatableQuestPatterns(questPatterns.toMutableList(), maxCount)
                }
    }

    private fun addRepeatableQuestPatterns(selectedPatterns: MutableList<QuestPattern>, maxCount: Int): Single<List<QuestPattern>> {
        return questPatternsRepository.getRepeatableDailyQuestPatterns()
                .map { questPatterns -> selectPatterns(questPatterns, maxCount - selectedPatterns.size) }
                .map { questPatterns ->
                    selectedPatterns.addAll(questPatterns)
                    selectedPatterns
                }
    }

    private fun selectPatterns(patternList: List<QuestPattern>, maxCount: Int): List<QuestPattern> {
        val selectedSet = HashSet<QuestPattern>()
        if (patternList.size <= maxCount) {
            selectedSet.addAll(patternList)
        } else {
            while (selectedSet.size < maxCount) {
                selectedSet.add(
                        patternList[random.nextInt(patternList.size - 1)]
                )
            }
        }
        return ArrayList(selectedSet)
    }

    private fun createUserTaskFromPattern(pattern: QuestPattern): UserTask {
        return UserTask(AUTO_ID,
                pattern.questId,
                false,
                calcTargetCount(pattern).toLong(),
                START_PROGRESS.toLong())
    }

    private fun calcTargetCount(questPattern: QuestPattern) =
            when (questPattern.questType) {
                QuestType.ONCE -> ONCE_DEFAULT_TARGET
                QuestType.COUNT -> COUNT_DEFAULT_TARGET
                else -> throw IllegalArgumentException("QuestType not supported: " + questPattern.questType)
            }
}

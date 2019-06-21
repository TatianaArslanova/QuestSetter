package com.example.ama.questapp.domain.engine

import com.example.ama.questapp.data.db.model.UserTask
import io.reactivex.Completable

interface QuestEngineGateway {

    fun onIncrementQuestProgress(userTask: UserTask): Completable

    fun addNewRandomDailyQuests(maxCount: Int): Completable
}
package com.example.ama.questapp.presentation.interactor

import com.example.ama.questapp.data.db.model.UserTask
import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern
import io.reactivex.Completable
import io.reactivex.Flowable

interface ActiveQuestsInteractor {

    fun completeQuest(userTask: UserTask): Completable

    fun loadAllUserQuests(): Flowable<List<UserTaskWithPattern>>
}
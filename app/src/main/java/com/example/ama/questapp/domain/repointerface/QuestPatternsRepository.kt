package com.example.ama.questapp.domain.repointerface

import com.example.ama.questapp.data.db.model.QuestPattern
import io.reactivex.Single

interface QuestPatternsRepository {

    fun getUnusedDailyQuestPatterns(): Single<List<QuestPattern>>

    fun getRepeatableDailyQuestPatterns(): Single<List<QuestPattern>>
}
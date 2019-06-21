package com.example.ama.questapp.data.repo

import com.example.ama.questapp.data.db.dao.QuestPatternDao
import com.example.ama.questapp.data.db.model.QuestPattern
import com.example.ama.questapp.data.db.model.QuestType
import com.example.ama.questapp.domain.repointerface.QuestPatternsRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestPatternsRepositoryImpl @Inject constructor(
        private val questPatternDao: QuestPatternDao
) : QuestPatternsRepository {
    override fun getUnusedDailyQuestPatterns(): Single<List<QuestPattern>> {
        return questPatternDao.getAllUnusedQuests()
                .doOnSubscribe { questPatternDao.insertAllQuestPatterns(getPatterns()) }
                .subscribeOn(Schedulers.io())
    }

    override fun getRepeatableDailyQuestPatterns(): Single<List<QuestPattern>> {
        return questPatternDao.getAllNotCurrentCompletedQuests()
                .subscribeOn(Schedulers.io())
    }

    private fun getPatterns(): List<QuestPattern> {
        val list = ArrayList<QuestPattern>()
        list.add(QuestPattern(0, "NAME111!", "lkj;lkj;lkj", QuestType.COUNT))
        list.add(QuestPattern(0, "NAME222!", "lkjfasdf;lkj;lkj", QuestType.COUNT))
        list.add(QuestPattern(0, "NAME333!", "lkj;lkjfdfdfdfdf;lkj", QuestType.COUNT))
        list.add(QuestPattern(0, "NAME444!", "lkj;l121212121kj;lkj", QuestType.COUNT))

        return list
    }
}
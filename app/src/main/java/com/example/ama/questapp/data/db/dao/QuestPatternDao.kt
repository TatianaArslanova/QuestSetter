package com.example.ama.questapp.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.ama.questapp.data.db.model.QuestPattern
import io.reactivex.Single

@Dao
interface QuestPatternDao {

    @Insert
    fun insertAllQuestPatterns(patterns: List<QuestPattern>)

    @Query("SELECT * FROM quest_pattern")
    fun getAllQuestPatterns(): Single<List<QuestPattern>>

    @Query("SELECT * FROM quest_pattern WHERE questId NOT IN (SELECT DISTINCT patternId FROM user_task)")
    fun getAllUnusedQuests(): Single<List<QuestPattern>>

    @Query("""SELECT * FROM quest_pattern WHERE questId
            IN (SELECT DISTINCT patternId FROM user_task WHERE isCompleted=1
            AND patternId NOT IN(SELECT patternId FROM user_task WHERE isCompleted=0))""")
    fun getAllNotCurrentCompletedQuests(): Single<List<QuestPattern>>
}
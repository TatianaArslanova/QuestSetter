package com.example.ama.questapp.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.ama.questapp.data.db.model.QuestPattern;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface QuestPatternDao {

    @Insert
    void insertAllQuestPatterns(List<QuestPattern> patterns);

    @Query("SELECT * FROM quest_pattern")
    Single<List<QuestPattern>> getAllQuestPatterns();

    @Query("SELECT * FROM quest_pattern WHERE questId NOT IN (SELECT DISTINCT patternId FROM user_task)")
    List<QuestPattern> getAllNotUsedQuests();

    @Query("SELECT * FROM quest_pattern WHERE questId " +
            "IN (SELECT DISTINCT patternId FROM user_task WHERE isCompleted=1 " +
            "AND patternId NOT IN(SELECT patternId FROM user_task WHERE isCompleted=0))")
    List<QuestPattern> getAllNotCurrentCompletedQuests();
}

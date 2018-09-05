package com.example.ama.questapp.repo.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.ama.questapp.repo.model.QuestPattern;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface QuestDao {

    @Insert
    void insertAllQuestPatterns(List<QuestPattern> patterns);

    @Query("SELECT * FROM patterns")
    Single<List<QuestPattern>> getAllQuestPatterns();

    @Query("SELECT * FROM patterns WHERE questId NOT IN (SELECT DISTINCT patternId FROM usertasks)")
    List<QuestPattern> getAllNotUsedQuests();

    @Query("SELECT * FROM patterns WHERE questId " +
            "IN (SELECT DISTINCT patternId FROM usertasks WHERE isCompleted=1 " +
            "AND patternId NOT IN(SELECT patternId FROM usertasks WHERE isCompleted=0))")
    List<QuestPattern> getAllNotCurrentCompletedQuests();
}

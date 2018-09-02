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

    @Query("SELECT * FROM patterns INNER JOIN usertasks ON usertasks.patternId = patterns.questId")
    Single<List<QuestPattern>> getAllUserQuests();
}

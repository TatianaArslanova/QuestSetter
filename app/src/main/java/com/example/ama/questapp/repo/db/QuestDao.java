package com.example.ama.questapp.repo.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.ama.questapp.repo.model.QuestPattern;
import com.example.ama.questapp.repo.model.QuestStatus;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface QuestDao {

    @Insert
    void insertAllQuestPatterns(List<QuestPattern> patterns);

    @Insert
    void insertAllQuestStatuses(List<QuestStatus> questStatuses);

    @Query("SELECT * FROM patterns")
    Single<List<QuestPattern>> getAllQuestPatterns();

    @Query("SELECT * FROM patterns INNER JOIN statuses ON statuses.patternId = patterns.id")
    Single<List<QuestPattern>> getAllUserQuests();

    @Query("SELECT * FROM patterns INNER JOIN statuses ON (statuses.patternId = patterns.id AND statuses.isCompleted = 0)")
    Single<List<QuestPattern>> getNotCompletedUserQuests();

    @Query("SELECT * FROM patterns INNER JOIN statuses ON (statuses.patternId = patterns.id AND statuses.isCompleted = 1)")
    Single<List<QuestPattern>> getCompletedUserQuests();
}

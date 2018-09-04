package com.example.ama.questapp.repo.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.ama.questapp.repo.model.PatternWithStatus;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface PatternWithStatusesDao {

    @Query("SELECT * FROM usertasks, patterns WHERE usertasks.patternId=patterns.questId ORDER BY usertasks.isCompleted")
    Flowable<List<PatternWithStatus>> getAllUserQuests();

    @Query("SELECT * FROM patterns INNER JOIN usertasks ON (usertasks.patternId = patterns.questId AND usertasks.isCompleted = 0)")
    Single<List<PatternWithStatus>> getNotCompletedUserQuests();

    @Query("SELECT * FROM patterns INNER JOIN usertasks ON (usertasks.patternId = patterns.questId AND usertasks.isCompleted = 1)")
    Single<List<PatternWithStatus>> getCompletedUserQuests();
}

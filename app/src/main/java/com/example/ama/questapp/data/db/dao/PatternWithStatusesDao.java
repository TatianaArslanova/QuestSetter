package com.example.ama.questapp.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.ama.questapp.data.db.model.pojo.PatternWithStatus;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface PatternWithStatusesDao {

    @Query("SELECT * FROM usertasks, patterns WHERE usertasks.patternId=patterns.questId ORDER BY usertasks.isCompleted")
    Flowable<List<PatternWithStatus>> getAllUserQuests();
}

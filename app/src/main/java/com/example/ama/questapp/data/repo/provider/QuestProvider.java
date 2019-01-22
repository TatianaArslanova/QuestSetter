package com.example.ama.questapp.data.repo.provider;

import com.example.ama.questapp.data.db.model.pojo.PatternWithStatus;

import java.util.List;

import io.reactivex.Flowable;

public interface QuestProvider {
    Flowable<List<PatternWithStatus>> loadAllUserTasks();
}

package com.example.ama.questapp.repo.provider;

import com.example.ama.questapp.repo.model.pojo.PatternWithStatus;

import java.util.List;

import io.reactivex.Flowable;

public interface QuestProvider {
    Flowable<List<PatternWithStatus>> loadAllUserQuests();
}

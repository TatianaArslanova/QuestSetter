package com.example.ama.questapp.domain.engine;

import com.example.ama.questapp.data.db.model.UserTask;

import io.reactivex.Completable;

public interface QuestEngineGateway {

    Completable onIncrementQuestProgress(UserTask userTask);

    Completable addNewRandomDailyQuests(int maxCount);
}

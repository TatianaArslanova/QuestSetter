package com.example.ama.questapp.domain.interactor;

import com.example.ama.questapp.data.db.model.UserTask;
import com.example.ama.questapp.data.db.model.pojo.PatternWithStatus;

import java.util.List;

import io.reactivex.Flowable;

public interface QuestMainListInteractor {
    void completeQuest(UserTask userTask);

    Flowable<List<PatternWithStatus>> loadAllUserQuests();
}



package com.example.ama.questapp.presentation.interactor;

import com.example.ama.questapp.data.db.model.UserTask;
import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface CurrentQuestsInteractor {
    Completable completeQuest(UserTask userTask);

    Flowable<List<UserTaskWithPattern>> loadAllUserQuests();
}



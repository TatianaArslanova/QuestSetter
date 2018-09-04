package com.example.ama.questapp.ui.list.interactor;

import com.example.ama.questapp.repo.model.UserQuest;
import com.example.ama.questapp.repo.model.pojo.PatternWithStatus;
import com.example.ama.questapp.ui.base.ViewState;

import java.util.List;

import io.reactivex.Flowable;

public interface QuestMainListInteractor {
    void completeQuest(UserQuest userQuest);

    Flowable<ViewState<List<PatternWithStatus>>> loadAllUserQuests();
}



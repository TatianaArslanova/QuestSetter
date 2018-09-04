package com.example.ama.questapp.ui.list.interactor;

import com.example.ama.questapp.repo.model.PatternWithStatus;
import com.example.ama.questapp.repo.model.UserTask;
import com.example.ama.questapp.ui.base.ViewState;

import java.util.List;

import io.reactivex.Flowable;

public interface QuestMainListInteractor {
    void updateStatus(UserTask status);

    Flowable<ViewState<List<PatternWithStatus>>> loadAllUserQuests();
}


package com.example.ama.questapp.domain.interactor;

import com.example.ama.questapp.data.db.model.UserQuest;
import com.example.ama.questapp.data.db.model.pojo.PatternWithStatus;
import com.example.ama.questapp.presentation.base.ViewState;

import java.util.List;

import io.reactivex.Flowable;

public interface QuestMainListInteractor {
    void completeQuest(UserQuest userQuest);

    Flowable<ViewState<List<PatternWithStatus>>> loadAllUserQuests();
}



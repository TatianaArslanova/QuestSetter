package com.example.ama.questapp.domain.interactor;

import com.example.ama.questapp.domain.engine.UserQuestEngine;
import com.example.ama.questapp.data.db.model.UserQuest;
import com.example.ama.questapp.data.db.model.pojo.PatternWithStatus;
import com.example.ama.questapp.data.repo.provider.QuestProvider;
import com.example.ama.questapp.presentation.base.ViewState;
import com.example.ama.questapp.presentation.base.ViewStateFactory;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class QuestMainListInteractorImpl implements QuestMainListInteractor {

    private QuestProvider questProvider;
    private UserQuestEngine userQuestEngine;

    public QuestMainListInteractorImpl(QuestProvider questProvider,
                                       UserQuestEngine userQuestEngine) {
        this.questProvider = questProvider;
        this.userQuestEngine = userQuestEngine;
    }

    @Override
    public void completeQuest(UserQuest userQuest) {
        userQuestEngine.incrementQuestProgress(userQuest);
    }

    @Override
    public Flowable<List<PatternWithStatus>> loadAllUserQuests() {
        return questProvider.loadAllUserQuests()
                .subscribeOn(Schedulers.io());
    }
}

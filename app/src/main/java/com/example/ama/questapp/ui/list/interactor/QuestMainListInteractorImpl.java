package com.example.ama.questapp.ui.list.interactor;

import com.example.ama.questapp.engine.UserQuestEngine;
import com.example.ama.questapp.repo.model.UserQuest;
import com.example.ama.questapp.repo.model.pojo.PatternWithStatus;
import com.example.ama.questapp.repo.provider.QuestProvider;
import com.example.ama.questapp.ui.base.ViewState;
import com.example.ama.questapp.ui.base.ViewStateFactory;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

public class QuestMainListInteractorImpl implements QuestMainListInteractor {

    private QuestProvider questProvider;
    private ViewStateFactory<List<PatternWithStatus>> stateFactory;
    private UserQuestEngine userQuestEngine;

    public QuestMainListInteractorImpl(QuestProvider questProvider,
                                       ViewStateFactory<List<PatternWithStatus>> stateFactory,
                                       UserQuestEngine userQuestEngine) {
        this.questProvider = questProvider;
        this.stateFactory = stateFactory;
        this.userQuestEngine = userQuestEngine;
    }

    @Override
    public void completeQuest(final UserQuest userQuest) {
        userQuestEngine.incrementQuestProgress(userQuest);
    }

    @Override
    public Flowable<ViewState<List<PatternWithStatus>>> loadAllUserQuests() {
        return questProvider.loadAllUserQuests()
                .map(new Function<List<PatternWithStatus>, ViewState<List<PatternWithStatus>>>() {
                    @Override
                    public ViewState<List<PatternWithStatus>> apply(List<PatternWithStatus> patternWithStatuses) throws Exception {
                        if (patternWithStatuses.isEmpty()) {
                            return stateFactory.empty();
                        } else {
                            return stateFactory.hasData(patternWithStatuses);
                        }
                    }
                })
                .startWith(stateFactory.loading())
                .onErrorReturn(new Function<Throwable, ViewState<List<PatternWithStatus>>>() {
                    @Override
                    public ViewState<List<PatternWithStatus>> apply(Throwable throwable) throws Exception {
                        return stateFactory.error(throwable);
                    }
                });
    }
}

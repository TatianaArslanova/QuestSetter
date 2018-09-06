package com.example.ama.questapp.ui.list.di;

import com.example.ama.questapp.repo.provider.QuestDatabaseProvider;
import com.example.ama.questapp.engine.UserQuestEngine;
import com.example.ama.questapp.repo.model.pojo.PatternWithStatus;
import com.example.ama.questapp.repo.provider.QuestProvider;
import com.example.ama.questapp.ui.base.ViewStateFactory;
import com.example.ama.questapp.ui.list.interactor.QuestMainListInteractorImpl;
import com.example.ama.questapp.ui.list.mvp.QuestMainListPresenter;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class ListModule {

    @Provides
    @MainQuestListScope
    QuestMainListInteractorImpl provideMainListInteractor(
            QuestProvider questProvider,
            ViewStateFactory<List<PatternWithStatus>> stateFactory,
            UserQuestEngine userQuestEngine) {
        return new QuestMainListInteractorImpl(questProvider, stateFactory, userQuestEngine);
    }

    @Provides
    @MainQuestListScope
    ViewStateFactory<List<PatternWithStatus>> provideViewStateFactory() {
        return new ViewStateFactory<>();
    }

    @Provides
    @MainQuestListScope
    QuestMainListPresenter provideQuestMainListPresenter(QuestMainListInteractorImpl interactor) {
        return new QuestMainListPresenter(interactor);
    }
}

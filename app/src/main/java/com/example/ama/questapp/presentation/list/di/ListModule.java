package com.example.ama.questapp.presentation.list.di;

import com.example.ama.questapp.domain.engine.UserQuestEngine;
import com.example.ama.questapp.data.db.model.pojo.PatternWithStatus;
import com.example.ama.questapp.data.repo.provider.QuestProvider;
import com.example.ama.questapp.presentation.base.Presenter;
import com.example.ama.questapp.presentation.base.ViewStateFactory;
import com.example.ama.questapp.domain.interactor.QuestMainListInteractor;
import com.example.ama.questapp.domain.interactor.QuestMainListInteractorImpl;
import com.example.ama.questapp.presentation.list.mvp.MainQuestListView;
import com.example.ama.questapp.presentation.list.mvp.QuestMainListPresenter;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class ListModule {

    @Provides
    @MainQuestListScope
    QuestMainListInteractor provideMainListInteractor(
            QuestProvider questProvider,
            UserQuestEngine userQuestEngine) {
        return new QuestMainListInteractorImpl(questProvider, userQuestEngine);
    }

    @Provides
    @MainQuestListScope
    ViewStateFactory<List<PatternWithStatus>> provideViewStateFactory() {
        return new ViewStateFactory<>();
    }

    @Provides
    @MainQuestListScope
    Presenter<MainQuestListView> provideQuestMainListPresenter(QuestMainListInteractor interactor,
                                                               ViewStateFactory<List<PatternWithStatus>> stateFactory) {
        return new QuestMainListPresenter(interactor, stateFactory);
    }
}

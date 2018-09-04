package com.example.ama.questapp.di;

import com.example.ama.questapp.repo.db.QuestDatabase;
import com.example.ama.questapp.repo.engine.DatabaseOperator;
import com.example.ama.questapp.repo.engine.UserQuestEngine;
import com.example.ama.questapp.repo.model.PatternWithStatus;
import com.example.ama.questapp.ui.base.ViewStateFactory;
import com.example.ama.questapp.ui.list.interactor.QuestMainListInteractorImpl;
import com.example.ama.questapp.ui.list.mvp.QuestMainListPresenter;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ListModule {

    @Provides
    @Singleton
    QuestMainListInteractorImpl provideMainListInteractor(
            DatabaseOperator databaseOperator,
            ViewStateFactory<List<PatternWithStatus>> stateFactory,
            UserQuestEngine userQuestEngine) {
        return new QuestMainListInteractorImpl(databaseOperator, stateFactory, userQuestEngine);
    }

    @Provides
    @Singleton
    ViewStateFactory<List<PatternWithStatus>> provideViewStateFactory() {
        return new ViewStateFactory<>();
    }

    @Provides
    @Singleton
    QuestMainListPresenter provideQuestMainListPresenter(QuestMainListInteractorImpl interactor){
        return new QuestMainListPresenter(interactor);
    }
}

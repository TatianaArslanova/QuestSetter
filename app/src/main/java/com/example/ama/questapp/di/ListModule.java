package com.example.ama.questapp.di;

import com.example.ama.questapp.repo.db.QuestDatabase;
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
            QuestDatabase database,
            ViewStateFactory<List<PatternWithStatus>> stateFactory) {
        return new QuestMainListInteractorImpl(database, stateFactory);
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

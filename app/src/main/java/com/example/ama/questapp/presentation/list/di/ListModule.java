package com.example.ama.questapp.presentation.list.di;

import com.example.ama.questapp.data.db.model.pojo.PatternWithStatus;
import com.example.ama.questapp.presentation.base.Presenter;
import com.example.ama.questapp.presentation.base.ViewStateFactory;
import com.example.ama.questapp.presentation.list.mvp.MainQuestListView;
import com.example.ama.questapp.presentation.list.mvp.QuestMainListPresenter;

import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ListModule {

    @Provides
    @MainQuestListScope
    static ViewStateFactory<List<PatternWithStatus>> provideViewStateFactory() {
        return new ViewStateFactory<>();
    }

    @Binds
    @MainQuestListScope
    abstract Presenter<MainQuestListView> provideQuestMainListPresenter(QuestMainListPresenter presenter);
}

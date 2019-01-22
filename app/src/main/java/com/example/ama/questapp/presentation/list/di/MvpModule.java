package com.example.ama.questapp.presentation.list.di;

import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern;
import com.example.ama.questapp.presentation.base.ViewStateFactory;
import com.example.ama.questapp.presentation.list.mvp.MainQuestListPresenter;
import com.example.ama.questapp.presentation.list.mvp.MainQuestListView;
import com.example.ama.questapp.presentation.list.mvp.QuestMainListPresenter;

import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MvpModule {

    @Provides
    @MainQuestListScope
    static ViewStateFactory<List<UserTaskWithPattern>> provideViewStateFactory() {
        return new ViewStateFactory<>();
    }

    @Binds
    @MainQuestListScope
    abstract MainQuestListPresenter<MainQuestListView> provideQuestMainListPresenter(QuestMainListPresenter presenter);
}

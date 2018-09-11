package com.example.ama.questapp.presentation.list.di;

import com.example.ama.questapp.presentation.list.QuestListFragment;
import com.example.ama.questapp.presentation.list.adapter.OnCompleteQuestClickListener;

import dagger.Module;
import dagger.Provides;

@Module
public class ListModule {
    private QuestListFragment fragment;

    public ListModule(QuestListFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @MainQuestListScope
    OnCompleteQuestClickListener provideListener() {
        return fragment::tryToCompleteQuest;
    }

}

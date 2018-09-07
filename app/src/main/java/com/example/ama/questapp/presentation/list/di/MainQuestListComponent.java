package com.example.ama.questapp.presentation.list.di;

import com.example.ama.questapp.presentation.list.QuestListFragment;

import dagger.Subcomponent;

@MainQuestListScope
@Subcomponent (modules = {ListModule.class})
public interface MainQuestListComponent {
    void inject(QuestListFragment questListFragment);
}

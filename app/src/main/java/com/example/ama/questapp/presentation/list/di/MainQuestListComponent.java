package com.example.ama.questapp.presentation.list.di;

import com.example.ama.questapp.presentation.list.QuestListFragment;

import dagger.Subcomponent;

@MainQuestListScope
@Subcomponent(modules = {MvpModule.class, ListModule.class})
public interface MainQuestListComponent {
    void inject(QuestListFragment questListFragment);

    @Subcomponent.Builder
    interface Builder {
        MainQuestListComponent.Builder ListModule(ListModule listModule);

        MainQuestListComponent build();
    }
}

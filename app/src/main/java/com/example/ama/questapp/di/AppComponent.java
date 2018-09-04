package com.example.ama.questapp.di;

import com.example.ama.questapp.ui.list.QuestListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DbModule.class, ListModule.class})
public interface AppComponent {
    void inject(QuestListFragment questListFragment);
    //TODO: Another Scope
}

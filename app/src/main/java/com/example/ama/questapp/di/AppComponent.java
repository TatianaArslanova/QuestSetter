package com.example.ama.questapp.di;

import com.example.ama.questapp.QuestApp;
import com.example.ama.questapp.presentation.list.di.MainQuestListComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DbModule.class, CurrentQuestModule.class})
public interface AppComponent {
    MainQuestListComponent.Builder mainQuestListComponent();

    void inject(QuestApp app);
}

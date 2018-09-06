package com.example.ama.questapp.di;

import com.example.ama.questapp.QuestApp;
import com.example.ama.questapp.service.ProducingQuestIntentService;
import com.example.ama.questapp.ui.list.di.MainQuestListComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DbModule.class, QuestEngineModule.class})
public interface AppComponent {
    MainQuestListComponent mainQuestListComponent();

    void inject(ProducingQuestIntentService service);

    void inject(QuestApp app);
}

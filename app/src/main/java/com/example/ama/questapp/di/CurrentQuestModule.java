package com.example.ama.questapp.di;

import com.example.ama.questapp.domain.engine.QuestEngine;
import com.example.ama.questapp.domain.engine.QuestEngineGateway;
import com.example.ama.questapp.domain.interactor.QuestMainListInteractor;
import com.example.ama.questapp.domain.interactor.QuestMainListInteractorImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class CurrentQuestModule {

    @Binds
    @Singleton
    abstract QuestMainListInteractor provideMainListInteractor(QuestMainListInteractorImpl interactor);

    @Binds
    @Singleton
    abstract QuestEngineGateway bindQuestEngine(QuestEngine engine);

}

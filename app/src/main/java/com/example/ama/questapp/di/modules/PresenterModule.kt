package com.example.ama.questapp.di.modules

import com.example.ama.questapp.presentation.list.mvp.QuestMainListContract
import com.example.ama.questapp.presentation.list.mvp.QuestMainListPresenter
import dagger.Binds
import dagger.Module

@Module
interface PresenterModule {

    @Binds
    fun provideQuestMainListPresenter(presenter: QuestMainListPresenter): QuestMainListContract.Presenter

}
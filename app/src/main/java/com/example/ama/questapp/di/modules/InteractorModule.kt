package com.example.ama.questapp.di.modules

import com.example.ama.questapp.domain.interactor.CurrentQuestsInteractorImpl
import com.example.ama.questapp.presentation.interactor.CurrentQuestsInteractor
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface InteractorModule {

    @Binds
    @Singleton
    fun provideCurrentQuestsInteractor(interactor: CurrentQuestsInteractorImpl): CurrentQuestsInteractor

}
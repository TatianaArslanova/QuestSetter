package com.example.ama.questapp.di.modules

import com.example.ama.questapp.domain.interactor.ActiveQUestsInteractorImpl
import com.example.ama.questapp.presentation.interactor.ActiveQuestsInteractor
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface InteractorModule {

    @Binds
    @Singleton
    fun provideCurrentQuestsInteractor(interactor: ActiveQUestsInteractorImpl): ActiveQuestsInteractor

}
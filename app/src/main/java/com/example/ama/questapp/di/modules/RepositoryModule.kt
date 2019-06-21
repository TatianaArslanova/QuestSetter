package com.example.ama.questapp.di.modules

import com.example.ama.questapp.data.repo.QuestPatternsRepositoryImpl
import com.example.ama.questapp.data.repo.UserTaskRepositotyImpl
import com.example.ama.questapp.domain.repointerface.QuestPatternsRepository
import com.example.ama.questapp.domain.repointerface.UserTaskRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideUserTaskRepo(userTaskRepository: UserTaskRepositotyImpl): UserTaskRepository

    @Binds
    @Singleton
    fun provideQuestPatternsRepo(questPatternsRepository: QuestPatternsRepositoryImpl): QuestPatternsRepository

}
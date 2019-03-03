package com.example.ama.questapp.di.modules

import com.example.ama.questapp.domain.engine.QuestEngine
import com.example.ama.questapp.domain.engine.QuestEngineGateway
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface EngineModule {

    @Binds
    @Singleton
    fun bindQuestEngine(engine: QuestEngine): QuestEngineGateway

}
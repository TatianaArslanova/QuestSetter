package com.example.ama.questapp.di

import android.content.Context
import com.example.ama.questapp.QuestApp
import com.example.ama.questapp.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    FragmentModule::class,
    EngineModule::class,
    InteractorModule::class,
    RepositoryModule::class,
    ViewStateFactoryModule::class,
    DatabaseModule::class])

interface AppComponent : AndroidInjector<QuestApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun app(context: Context): Builder

        fun build(): AppComponent
    }
}
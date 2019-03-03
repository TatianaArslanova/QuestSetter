package com.example.ama.questapp.di.modules

import com.example.ama.questapp.presentation.addquest.AddQuestFragment
import com.example.ama.questapp.presentation.list.QuestListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector(modules = [ListModule::class])
    fun bindQuestListFragment(): QuestListFragment

    @ContributesAndroidInjector
    fun bindAddQuestFragment(): AddQuestFragment
}
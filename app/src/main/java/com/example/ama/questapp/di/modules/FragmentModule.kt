package com.example.ama.questapp.di.modules

import com.example.ama.questapp.presentation.addquest.AddQuestFragment
import com.example.ama.questapp.presentation.list.ActiveQuestsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun bindQuestListFragment(): ActiveQuestsFragment

    @ContributesAndroidInjector
    fun bindAddQuestFragment(): AddQuestFragment
}
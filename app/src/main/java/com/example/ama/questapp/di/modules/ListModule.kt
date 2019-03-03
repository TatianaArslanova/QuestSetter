package com.example.ama.questapp.di.modules

import com.example.ama.questapp.presentation.list.QuestListFragment
import com.example.ama.questapp.presentation.list.adapter.OnCompleteQuestClickListener
import dagger.Module
import dagger.Provides

@Module
class ListModule {

    @Provides
    fun provideListener(fragment: QuestListFragment): OnCompleteQuestClickListener =
            OnCompleteQuestClickListener { fragment.tryToCompleteQuest(it) }

}
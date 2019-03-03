package com.example.ama.questapp.di.modules

import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern
import com.example.ama.questapp.presentation.base.ViewStateFactory
import dagger.Module
import dagger.Provides

@Module
class ViewStateFactoryModule {

    @Provides
    fun provideViewStateFactory(): ViewStateFactory<List<UserTaskWithPattern>> {
        return ViewStateFactory()
    }
}
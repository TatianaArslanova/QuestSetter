package com.example.ama.questapp.di.modules

import com.example.ama.questapp.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity
}
package com.example.ama.questapp.presentation.base

import android.os.Bundle
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
}
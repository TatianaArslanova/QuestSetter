package com.example.ama.questapp.presentation.base

import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter
import moxy.MvpView

abstract class BasePresenter<T : MvpView> : MvpPresenter<T>() {

    protected val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}
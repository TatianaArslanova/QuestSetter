package com.example.ama.questapp.presentation.base

interface BaseContract {

    interface QuestView

    interface Presenter<T : QuestView> {

        fun attachView(view: T)

        fun detachView()
    }
}
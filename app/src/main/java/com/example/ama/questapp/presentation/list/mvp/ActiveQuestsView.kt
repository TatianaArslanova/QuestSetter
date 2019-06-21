package com.example.ama.questapp.presentation.list.mvp

import com.example.ama.questapp.data.db.model.UserTask
import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern
import com.example.ama.questapp.presentation.base.ViewState
import moxy.MvpView

interface ActiveQuestsView : MvpView {

    fun render(viewState: ViewState<List<UserTaskWithPattern>>)

    fun tryToCompleteQuest(userTask: UserTask)
}

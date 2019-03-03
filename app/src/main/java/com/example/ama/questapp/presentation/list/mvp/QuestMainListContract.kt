package com.example.ama.questapp.presentation.list.mvp

import com.example.ama.questapp.data.db.model.UserTask
import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern
import com.example.ama.questapp.presentation.base.BaseContract
import com.example.ama.questapp.presentation.base.ViewState

interface QuestMainListContract {

    interface View : BaseContract.QuestView {

        fun render(viewState: ViewState<List<UserTaskWithPattern>>)

        fun tryToCompleteQuest(userTask: UserTask)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun tryToCompleteQuest(userTask: UserTask)
    }
}
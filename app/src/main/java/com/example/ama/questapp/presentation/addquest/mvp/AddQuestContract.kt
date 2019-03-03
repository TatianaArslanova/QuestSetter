package com.example.ama.questapp.presentation.addquest.mvp

import com.example.ama.questapp.presentation.base.BaseContract

interface AddQuestContract {

    interface View : BaseContract.QuestView

    interface Presenter : BaseContract.Presenter<View>

}
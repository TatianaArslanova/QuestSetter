package com.example.ama.questapp.presentation.list.mvp

import com.example.ama.questapp.data.db.model.UserTask
import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern
import com.example.ama.questapp.presentation.base.BasePresenter
import com.example.ama.questapp.presentation.base.ViewStateFactory
import com.example.ama.questapp.presentation.interactor.ActiveQuestsInteractor
import com.example.ama.questapp.utils.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class ActiveQuestsPresenter @Inject constructor(
        private val interactor: ActiveQuestsInteractor,
        private val viewStateFactory: ViewStateFactory<List<UserTaskWithPattern>>
) : BasePresenter<ActiveQuestsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getData()
    }

    private fun getData() {
        interactor.loadAllUserQuests()
                .map { patternsWithStatuses ->
                    if (patternsWithStatuses.isEmpty()) {
                        viewStateFactory.empty()
                    } else {
                        viewStateFactory.hasData(patternsWithStatuses)
                    }
                }
                .startWith(viewStateFactory.loading())
                .onErrorReturn { throwable -> viewStateFactory.error(throwable) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(viewState::render, Timber::e)
                .addTo(compositeDisposable)
    }

    fun tryToCompleteQuest(userTask: UserTask) {
        interactor.completeQuest(userTask)
                .subscribe({}, Timber::e)
                .addTo(compositeDisposable)
    }
}
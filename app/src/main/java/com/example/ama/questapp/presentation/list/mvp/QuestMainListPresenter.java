package com.example.ama.questapp.presentation.list.mvp;

import com.example.ama.questapp.data.db.model.UserTask;
import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern;
import com.example.ama.questapp.presentation.base.BasePresenter;
import com.example.ama.questapp.presentation.base.ViewStateFactory;
import com.example.ama.questapp.presentation.interactor.CurrentQuestsInteractor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class QuestMainListPresenter
        extends BasePresenter<QuestMainListContract.View>
        implements QuestMainListContract.Presenter {

    private CurrentQuestsInteractor interactor;
    private ViewStateFactory<List<UserTaskWithPattern>> stateFactory;

    @Inject
    public QuestMainListPresenter(CurrentQuestsInteractor interactor, ViewStateFactory<List<UserTaskWithPattern>> stateFactory) {
        this.interactor = interactor;
        this.stateFactory = stateFactory;
    }

    @Override
    public void attachView(QuestMainListContract.View view) {
        super.attachView(view);
        loadData();
    }

    private void loadData() {
        disposable.add(interactor.loadAllUserQuests()
                .map(patternWithStatuses -> {
                    if (patternWithStatuses.isEmpty()) {
                        return stateFactory.empty();
                    } else {
                        return stateFactory.hasData(patternWithStatuses);
                    }
                })
                .startWith(stateFactory.loading())
                .onErrorReturn(throwable -> stateFactory.error(throwable))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listViewState -> {
                    if (view != null) {
                        view.render(listViewState);
                    }
                }, Timber::e));
    }

    @Override
    public void tryToCompleteQuest(UserTask userTask) {
        disposable.add(interactor.completeQuest(userTask)
                .subscribe(() -> {
                }, Timber::e));
    }
}

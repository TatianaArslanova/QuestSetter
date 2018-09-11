package com.example.ama.questapp.presentation.list.mvp;

import com.example.ama.questapp.data.db.model.UserQuest;
import com.example.ama.questapp.data.db.model.pojo.PatternWithStatus;
import com.example.ama.questapp.domain.interactor.QuestMainListInteractor;
import com.example.ama.questapp.presentation.base.BasePresenter;
import com.example.ama.questapp.presentation.base.ViewStateFactory;
import com.example.ama.questapp.presentation.list.di.MainQuestListScope;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@MainQuestListScope
public class QuestMainListPresenter
        extends BasePresenter<MainQuestListView>
        implements MainQuestListPresenter<MainQuestListView> {

    private QuestMainListInteractor interactor;
    private ViewStateFactory<List<PatternWithStatus>> stateFactory;

    @Inject
    public QuestMainListPresenter(QuestMainListInteractor interactor, ViewStateFactory<List<PatternWithStatus>> stateFactory) {
        this.interactor = interactor;
        this.stateFactory = stateFactory;
    }

    @Override
    public void attachView(MainQuestListView view) {
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
                }));
    }

    @Override
    public void tryToCompleteQuest(UserQuest userQuest) {
        disposable.add(Completable.fromAction(
                () -> interactor.completeQuest(userQuest)
        )
                .subscribeOn(Schedulers.io())
                .subscribe());
    }
}

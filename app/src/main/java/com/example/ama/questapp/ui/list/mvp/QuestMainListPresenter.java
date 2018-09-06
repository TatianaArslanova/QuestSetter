package com.example.ama.questapp.ui.list.mvp;

import com.example.ama.questapp.repo.model.UserQuest;
import com.example.ama.questapp.repo.model.pojo.PatternWithStatus;
import com.example.ama.questapp.ui.base.BasePresenter;
import com.example.ama.questapp.ui.base.ViewState;
import com.example.ama.questapp.ui.list.interactor.QuestMainListInteractor;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class QuestMainListPresenter extends BasePresenter<MainQuestListView> {

    private QuestMainListInteractor interactor;

    public QuestMainListPresenter(QuestMainListInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(MainQuestListView view) {
        super.attachView(view);
        loadData();
        bindIntents();
    }

    @Override
    public void loadData() {
        disposable.add(interactor.loadAllUserQuests()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ViewState<List<PatternWithStatus>>>() {
                    @Override
                    public void accept(ViewState<List<PatternWithStatus>> listViewState) throws Exception {
                        if (view != null) {
                            view.render(listViewState);
                        }
                    }
                }));
    }

    private void bindIntents() {
        disposable.add(
                view.completeQuestIntent()
                        .observeOn(Schedulers.io())
                        .subscribe(new Consumer<UserQuest>() {
                            @Override
                            public void accept(UserQuest userQuest) throws Exception {
                                interactor.completeQuest(userQuest);
                            }
                        })
        );
    }
}

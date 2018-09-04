package com.example.ama.questapp.ui.list.interactor;

import com.example.ama.questapp.repo.db.QuestDatabase;
import com.example.ama.questapp.repo.model.PatternWithStatus;
import com.example.ama.questapp.repo.model.UserTask;
import com.example.ama.questapp.ui.base.ViewState;
import com.example.ama.questapp.ui.base.ViewStateFactory;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class QuestMainListInteractorImpl implements QuestMainListInteractor {

    private QuestDatabase database;
    private ViewStateFactory<List<PatternWithStatus>> stateFactory;

    public QuestMainListInteractorImpl(QuestDatabase database,
                                       ViewStateFactory<List<PatternWithStatus>> stateFactory) {
        this.database = database;
        this.stateFactory = stateFactory;
    }

    @Override
    public void updateStatus(final UserTask status) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                database.getStatusDao().updateStatus(status);
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @Override
    public Flowable<ViewState<List<PatternWithStatus>>> loadAllUserQuests() {
        return database.getPatternWithStatusesDao().getAllUserQuests()
                .map(new Function<List<PatternWithStatus>, ViewState<List<PatternWithStatus>>>() {
                    @Override
                    public ViewState<List<PatternWithStatus>> apply(List<PatternWithStatus> patternWithStatuses) throws Exception {
                        if (patternWithStatuses.isEmpty()) {
                            return stateFactory.empty();
                        } else {
                            return stateFactory.hasData(patternWithStatuses);
                        }
                    }
                })
                .startWith(stateFactory.loading())
                .onErrorReturn(new Function<Throwable, ViewState<List<PatternWithStatus>>>() {
                    @Override
                    public ViewState<List<PatternWithStatus>> apply(Throwable throwable) throws Exception {
                        return stateFactory.error(throwable);
                    }
                });
    }
}

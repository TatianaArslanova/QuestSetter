package com.example.ama.questapp.domain.interactor;

import com.example.ama.questapp.data.db.model.UserTask;
import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern;
import com.example.ama.questapp.data.repo.provider.QuestProvider;
import com.example.ama.questapp.domain.engine.UserQuestEngine;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class QuestMainListInteractorImpl implements QuestMainListInteractor {

    private QuestProvider questProvider;
    private UserQuestEngine userQuestEngine;

    @Inject
    public QuestMainListInteractorImpl(QuestProvider questProvider,
                                       UserQuestEngine userQuestEngine) {
        this.questProvider = questProvider;
        this.userQuestEngine = userQuestEngine;
    }

    @Override
    public void completeQuest(UserTask userTask) {
        userQuestEngine.incrementQuestProgress(userTask);
    }

    @Override
    public Flowable<List<UserTaskWithPattern>> loadAllUserQuests() {
        return questProvider.getAllUserTasksWithPattern()
                .subscribeOn(Schedulers.io());
    }
}

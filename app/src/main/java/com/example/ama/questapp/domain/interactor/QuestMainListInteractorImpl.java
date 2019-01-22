package com.example.ama.questapp.domain.interactor;

import com.example.ama.questapp.data.db.model.UserTask;
import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern;
import com.example.ama.questapp.data.repo.provider.UserTaskProvider;
import com.example.ama.questapp.domain.engine.UserQuestEngine;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class QuestMainListInteractorImpl implements QuestMainListInteractor {

    private UserTaskProvider userTaskProvider;
    private UserQuestEngine userQuestEngine;

    @Inject
    public QuestMainListInteractorImpl(UserTaskProvider questProvider,
                                       UserQuestEngine userQuestEngine) {
        this.userTaskProvider = questProvider;
        this.userQuestEngine = userQuestEngine;
    }

    @Override
    public void completeQuest(UserTask userTask) {
        userQuestEngine.incrementQuestProgress(userTask);
    }

    @Override
    public Flowable<List<UserTaskWithPattern>> loadAllUserQuests() {
        return userTaskProvider.getAllUserTasksWithPattern();
    }
}

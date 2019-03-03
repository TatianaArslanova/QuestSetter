package com.example.ama.questapp.domain.interactor;

import com.example.ama.questapp.data.db.model.UserTask;
import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern;
import com.example.ama.questapp.domain.repointerface.UserTaskRepository;
import com.example.ama.questapp.domain.engine.QuestEngine;
import com.example.ama.questapp.domain.engine.QuestEngineGateway;
import com.example.ama.questapp.presentation.interactor.CurrentQuestsInteractor;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Singleton
public class CurrentQuestsInteractorImpl implements CurrentQuestsInteractor {

    private UserTaskRepository userTaskRepository;
    private QuestEngineGateway userQuestEngine;

    @Inject
    public CurrentQuestsInteractorImpl(UserTaskRepository questProvider,
                                       QuestEngine userQuestEngine) {
        this.userTaskRepository = questProvider;
        this.userQuestEngine = userQuestEngine;
    }

    @Override
    public Completable completeQuest(UserTask userTask) {
        return userQuestEngine.onIncrementQuestProgress(userTask);
    }

    @Override
    public Flowable<List<UserTaskWithPattern>> loadAllUserQuests() {
        return userTaskRepository.getAllUserTasksWithPattern();
    }
}

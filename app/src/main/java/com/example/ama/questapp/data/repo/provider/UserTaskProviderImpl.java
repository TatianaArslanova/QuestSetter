package com.example.ama.questapp.data.repo.provider;

import com.example.ama.questapp.data.db.dao.QuestPatternDao;
import com.example.ama.questapp.data.db.dao.UserTaskDao;
import com.example.ama.questapp.data.db.dao.UserTaskWithPatternDao;
import com.example.ama.questapp.data.db.model.UserTask;
import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern;
import com.example.ama.questapp.domain.repointerface.UserTaskProvider;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class UserTaskProviderImpl implements UserTaskProvider {
    private UserTaskWithPatternDao userTaskWithPatternDao;
    private UserTaskDao userTaskDao;
    private QuestPatternDao questPatternDao;

    @Inject
    public UserTaskProviderImpl(UserTaskWithPatternDao userTaskWithPatternDao,
                                UserTaskDao userTaskDao,
                                QuestPatternDao questPatternDao) {
        this.userTaskWithPatternDao = userTaskWithPatternDao;
        this.userTaskDao = userTaskDao;
        this.questPatternDao = questPatternDao;
    }

    @Override
    public Flowable<List<UserTaskWithPattern>> getAllUserTasksWithPattern() {
        return userTaskWithPatternDao.getAllUserTasksWithPattern()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Completable addUserTasks(List<UserTask> userTasks) {
        return Completable.fromAction(() -> userTaskDao.insertAllUserTasks(userTasks))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Completable updateUserTask(UserTask userTask) {
        return Completable.fromAction(() -> userTaskDao.updateUserTask(userTask))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<List<UserTask>> getAllTasksByPatternId(int patternId) {
        return userTaskDao.getAllStatusesFromPatternId(patternId)
                .subscribeOn(Schedulers.io());
    }
}

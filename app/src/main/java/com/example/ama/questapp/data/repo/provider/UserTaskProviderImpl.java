package com.example.ama.questapp.data.repo.provider;

import com.example.ama.questapp.data.db.dao.UserTaskDao;
import com.example.ama.questapp.data.db.dao.UserTaskWithPatternDao;
import com.example.ama.questapp.data.db.model.UserTask;
import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class UserTaskProviderImpl implements UserTaskProvider {
    private UserTaskWithPatternDao userTaskWithPatternDao;
    private UserTaskDao userTaskDao;

    @Inject
    public UserTaskProviderImpl(UserTaskWithPatternDao userTaskWithPatternDao, UserTaskDao userTaskDao) {
        this.userTaskWithPatternDao = userTaskWithPatternDao;
        this.userTaskDao = userTaskDao;
    }

    @Override
    public Flowable<List<UserTaskWithPattern>> getAllUserTasksWithPattern() {
        return userTaskWithPatternDao.getAllUserTasksWithPattern()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public void addUserTasks(List<UserTask> userTasks) {
        userTaskDao.insertAllUserTasks(userTasks);
    }

    @Override
    public void updateUserTask(UserTask userTask) {
        userTaskDao.updateUserTask(userTask);
    }

    @Override
    public List<UserTask> getAllTasksByPatternId(int patternId) {
        return userTaskDao.getAllStatusesFromPatternId(patternId);
    }
}

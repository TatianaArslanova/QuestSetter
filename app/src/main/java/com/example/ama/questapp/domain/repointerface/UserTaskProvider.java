package com.example.ama.questapp.domain.repointerface;

import com.example.ama.questapp.data.db.model.UserTask;
import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface UserTaskProvider {
    Completable addUserTasks(List<UserTask> userTasks);

    Completable updateUserTask(UserTask userTask);

    Single<List<UserTask>> getAllTasksByPatternId(int patternId);

    Flowable<List<UserTaskWithPattern>> getAllUserTasksWithPattern();
}

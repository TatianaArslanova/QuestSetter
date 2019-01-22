package com.example.ama.questapp.data.repo.provider;

import com.example.ama.questapp.data.db.model.UserTask;
import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern;

import java.util.List;

import io.reactivex.Flowable;

public interface UserTaskProvider {
    void addUserTasks(List<UserTask> userTasks);

    void updateUserTask(UserTask userTask);

    List<UserTask> getAllTasksByPatternId(int patternId);

    Flowable<List<UserTaskWithPattern>> getAllUserTasksWithPattern();
}

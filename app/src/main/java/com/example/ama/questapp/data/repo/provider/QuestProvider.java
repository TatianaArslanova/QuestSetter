package com.example.ama.questapp.data.repo.provider;

import com.example.ama.questapp.data.db.model.pojo.UserTaskWithPattern;

import java.util.List;

import io.reactivex.Flowable;

public interface QuestProvider {
    Flowable<List<UserTaskWithPattern>> getAllUserTasksWithPattern();
}

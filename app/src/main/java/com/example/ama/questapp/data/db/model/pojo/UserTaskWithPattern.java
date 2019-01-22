package com.example.ama.questapp.data.db.model.pojo;

import android.arch.persistence.room.Embedded;

import com.example.ama.questapp.data.db.model.QuestPattern;
import com.example.ama.questapp.data.db.model.UserTask;

public class UserTaskWithPattern {

    @Embedded
    private UserTask userTask;

    @Embedded
    private QuestPattern pattern;

    public UserTask getUserTask() {
        return userTask;
    }

    public void setUserTask(UserTask userTask) {
        this.userTask = userTask;
    }

    public QuestPattern getPattern() {
        return pattern;
    }

    public void setPattern(QuestPattern pattern) {
        this.pattern = pattern;
    }
}

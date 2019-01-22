package com.example.ama.questapp.data.db.model.pojo;

import android.arch.persistence.room.Embedded;

import com.example.ama.questapp.data.db.model.QuestPattern;
import com.example.ama.questapp.data.db.model.UserTask;

public class PatternWithStatus {

    @Embedded
    private QuestPattern pattern;

    @Embedded
    private UserTask userTask;

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

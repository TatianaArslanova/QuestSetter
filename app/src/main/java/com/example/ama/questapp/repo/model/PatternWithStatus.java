package com.example.ama.questapp.repo.model;

import android.arch.persistence.room.Embedded;

public class PatternWithStatus {

    @Embedded
    private QuestPattern pattern;

    @Embedded
    private UserTask status;

    public UserTask getStatus() {
        return status;
    }

    public void setStatus(UserTask status) {
        this.status = status;
    }

    public QuestPattern getPattern() {
        return pattern;
    }

    public void setPattern(QuestPattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return pattern.getQuestName() + " " + status.isCompleted();
    }
}

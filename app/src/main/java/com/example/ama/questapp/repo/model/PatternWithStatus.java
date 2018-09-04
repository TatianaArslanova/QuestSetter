package com.example.ama.questapp.repo.model;

import android.arch.persistence.room.Embedded;

public class PatternWithStatus {

    @Embedded
    private QuestPattern pattern;

    @Embedded
    private UserQuest userQuest;

    public UserQuest getUserQuest() {
        return userQuest;
    }

    public void setUserQuest(UserQuest userQuest) {
        this.userQuest = userQuest;
    }

    public QuestPattern getPattern() {
        return pattern;
    }

    public void setPattern(QuestPattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return pattern.getQuestName() + " " + userQuest.isCompleted();
    }
}

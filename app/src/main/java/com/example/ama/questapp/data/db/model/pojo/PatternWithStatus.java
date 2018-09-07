package com.example.ama.questapp.data.db.model.pojo;

import android.arch.persistence.room.Embedded;

import com.example.ama.questapp.data.db.model.QuestPattern;
import com.example.ama.questapp.data.db.model.UserQuest;

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
}

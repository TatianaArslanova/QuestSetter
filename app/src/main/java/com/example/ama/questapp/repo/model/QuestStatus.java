package com.example.ama.questapp.repo.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "statuses")
public class QuestStatus {

    @PrimaryKey (autoGenerate = true)
    private final int id;
    private final boolean isCompleted;
    private final int patternId;

    public QuestStatus(int id, boolean isCompleted, int patternId) {
        this.id=id;
        this.isCompleted = isCompleted;
        this.patternId = patternId;
    }

    public int getId() {
        return id;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public int getPatternId() {
        return patternId;
    }
}

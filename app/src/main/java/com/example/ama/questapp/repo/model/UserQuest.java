package com.example.ama.questapp.repo.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "usertasks",
        foreignKeys = @ForeignKey(
                entity = QuestPattern.class,
                parentColumns = "questId",
                childColumns = "patternId"))
public class UserQuest {

    @PrimaryKey(autoGenerate = true)
    private final int taskId;
    private boolean isCompleted;
    private final int patternId;

    public UserQuest(int taskId, boolean isCompleted, int patternId) {
        this.taskId = taskId;
        this.isCompleted = isCompleted;
        this.patternId = patternId;
    }

    public int getTaskId() {
        return taskId;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public int getPatternId() {
        return patternId;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}

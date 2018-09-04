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
    private final int patternId;
    private boolean isCompleted;
    private final int taskTarget;
    private int taskProgress;

    public UserQuest(int taskId, int patternId, boolean isCompleted, int taskTarget, int taskProgress) {
        this.taskId = taskId;
        this.patternId = patternId;
        this.isCompleted = isCompleted;
        this.taskTarget = taskTarget;
        this.taskProgress = taskProgress;
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

    public int getTaskTarget() {
        return taskTarget;
    }

    public int getTaskProgress() {
        return taskProgress;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setTaskProgress(int taskProgress) {
        this.taskProgress = taskProgress;
    }
}

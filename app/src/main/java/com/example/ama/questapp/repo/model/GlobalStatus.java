package com.example.ama.questapp.repo.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "globalstatuses",
        foreignKeys = @ForeignKey(
        entity = QuestPattern.class,
        parentColumns = "questId",
        childColumns = "patternId"))
public class GlobalStatus {

    @PrimaryKey(autoGenerate = true)
    private final int globalStatusId;
    private final int patternId;
    private int countSum;

    public GlobalStatus(int globalStatusId, int patternId, int countSum) {
        this.globalStatusId = globalStatusId;
        this.patternId = patternId;
        this.countSum = countSum;
    }

    public int getGlobalStatusId() {
        return globalStatusId;
    }

    public int getPatternId() {
        return patternId;
    }

    public int getCountSum() {
        return countSum;
    }

    public void setCountSum(int countSum) {
        this.countSum = countSum;
    }
}

package com.example.ama.questapp.repo.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "patterns")
public class QuestPattern {

    @PrimaryKey(autoGenerate = true)
    private final int questId;
    private final String questName;
    private final String questDescription;

    public QuestPattern(int questId, String questName, String questDescription) {
        this.questId = questId;
        this.questName = questName;
        this.questDescription = questDescription;
    }

    public int getQuestId() {
        return questId;
    }

    public String getQuestName() {
        return questName;
    }

    public String getQuestDescription() {
        return questDescription;
    }
}

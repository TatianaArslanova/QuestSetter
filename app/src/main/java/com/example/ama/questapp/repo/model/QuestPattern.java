package com.example.ama.questapp.repo.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.ama.questapp.repo.db.converter.QuestTypeConverter;

@Entity(tableName = "patterns")
public class QuestPattern {

    @PrimaryKey(autoGenerate = true)
    private final int questId;
    private final String questName;
    private final String questDescription;

    @TypeConverters(QuestTypeConverter.class)
    private final QuestType questType;

    public QuestPattern(int questId, String questName, String questDescription, QuestType questType) {
        this.questId = questId;
        this.questName = questName;
        this.questDescription = questDescription;
        this.questType = questType;
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

    public QuestType getQuestType() {
        return questType;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof QuestPattern && this.questId == ((QuestPattern) obj).questId;
    }

    @Override
    public int hashCode() {
        return this.questId;
    }
}

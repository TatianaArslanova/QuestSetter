package com.example.ama.questapp.repo.db.converter;

import android.arch.persistence.room.TypeConverter;

import com.example.ama.questapp.repo.model.QuestType;

public class QuestTypeConverter {

    @TypeConverter
    public int questTypeToInt(QuestType type) {
        return type.getType();
    }

    @TypeConverter
    public QuestType intToQuestType(int type) {
        if (type == QuestType.ONCE.getType()) {
            return QuestType.ONCE;
        } else if (type == QuestType.COUNT.getType()) {
            return QuestType.COUNT;
        } else if (type == QuestType.TIME.getType()) {
            return QuestType.TIME;
        } else if (type == QuestType.AUTO_COUNT.getType()) {
            return QuestType.AUTO_COUNT;
        } else throw new IllegalArgumentException("Wrong QuestType");
    }
}

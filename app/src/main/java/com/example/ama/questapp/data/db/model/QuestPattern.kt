package com.example.ama.questapp.data.db.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverter
import android.arch.persistence.room.TypeConverters

@Entity(tableName = "quest_pattern")
data class QuestPattern(
        @PrimaryKey(autoGenerate = true)
        val questId: Long,
        val questName: String?,
        val questDescription: String?,
        @TypeConverters(Converter::class)
        val questType: QuestType
)

enum class QuestType {
    ONCE,
    COUNT,
    TIME,
    AUTO_COUNT
}

class Converter {
    @TypeConverter
    fun typeToString(type: QuestType) = type.name

    @TypeConverter
    fun stringToTestType(string: String) = QuestType.values().firstOrNull { it.name == string }
}
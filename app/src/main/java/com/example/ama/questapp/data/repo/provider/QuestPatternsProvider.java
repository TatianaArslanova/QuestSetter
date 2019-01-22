package com.example.ama.questapp.data.repo.provider;

import com.example.ama.questapp.data.db.model.QuestPattern;
import com.example.ama.questapp.data.db.model.UserTask;

import java.util.List;

public interface QuestPatternsProvider {

    List<QuestPattern> getUnusedDailyQuestPatterns();

    List<QuestPattern> getRepeatableDailyQuestPatterns();

}

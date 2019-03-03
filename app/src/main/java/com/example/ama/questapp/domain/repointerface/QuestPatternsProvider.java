package com.example.ama.questapp.domain.repointerface;

import com.example.ama.questapp.data.db.model.QuestPattern;
import com.example.ama.questapp.data.db.model.UserTask;

import java.util.List;
import java.util.Set;

import io.reactivex.Single;

public interface QuestPatternsProvider {

    Single<List<QuestPattern>> getUnusedDailyQuestPatterns();

    Single<List<QuestPattern>> getRepeatableDailyQuestPatterns();

}

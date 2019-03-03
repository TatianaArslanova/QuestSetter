package com.example.ama.questapp.domain.repointerface;

import com.example.ama.questapp.data.db.model.QuestPattern;
import java.util.List;

import io.reactivex.Single;

public interface QuestPatternsRepository {

    Single<List<QuestPattern>> getUnusedDailyQuestPatterns();

    Single<List<QuestPattern>> getRepeatableDailyQuestPatterns();

}

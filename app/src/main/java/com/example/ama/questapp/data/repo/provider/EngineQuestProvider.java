package com.example.ama.questapp.data.repo.provider;

import com.example.ama.questapp.data.db.model.GlobalStatus;
import com.example.ama.questapp.data.db.model.QuestPattern;
import com.example.ama.questapp.data.db.model.UserQuest;

import java.util.List;

/***
 * Interface defines operations with database for {@link com.example.ama.questapp.domain.engine.UserQuestEngine}
 */

public interface EngineQuestProvider {
    void addQuestsWithGlobalStatuses(List<UserQuest> userQuests,
                                     List<GlobalStatus> globalStatuses);

    void updateQuestWithGlobalStatus(UserQuest userQuest, GlobalStatus globalStatus);

    List<UserQuest> getAllStatusesFromPatternId(int patternId);

    GlobalStatus getGlobalStatusByPatternId(int patternId);

    List<QuestPattern> getUnusedDailyQuestPatterns();

    List<QuestPattern> getRepeatableDailyQuestPatterns();

}

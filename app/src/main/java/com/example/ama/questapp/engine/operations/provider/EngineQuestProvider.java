package com.example.ama.questapp.engine.operations.provider;

import com.example.ama.questapp.repo.model.GlobalStatus;
import com.example.ama.questapp.repo.model.QuestPattern;
import com.example.ama.questapp.repo.model.UserQuest;

import java.util.List;

/***
 * Interface defines operations with database for {@link com.example.ama.questapp.engine.UserQuestEngine}
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

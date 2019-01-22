package com.example.ama.questapp.data.repo.provider;

import com.example.ama.questapp.data.db.model.QuestPattern;
import com.example.ama.questapp.data.db.model.UserTask;

import java.util.List;

/***
 * Interface defines operations with database for {@link com.example.ama.questapp.domain.engine.UserQuestEngine}
 */

public interface EngineQuestProvider {
    void addUserTasks(List<UserTask> userTasks);

    void updateUserTask(UserTask userTask);

    List<UserTask> getAllTasksByPatternId(int patternId);

    List<QuestPattern> getUnusedDailyQuestPatterns();

    List<QuestPattern> getRepeatableDailyQuestPatterns();

}

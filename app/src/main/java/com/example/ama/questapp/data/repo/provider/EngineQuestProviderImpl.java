package com.example.ama.questapp.data.repo.provider;

import com.example.ama.questapp.data.db.QuestDatabase;
import com.example.ama.questapp.data.db.model.QuestPattern;
import com.example.ama.questapp.data.db.model.UserTask;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/***
 * Implementation of {@link EngineQuestProvider} that defines operations
 * with database for {@link com.example.ama.questapp.domain.engine.UserQuestEngine}
 */

@Singleton
public class EngineQuestProviderImpl implements EngineQuestProvider {
    private QuestDatabase database;

    @Inject
    public EngineQuestProviderImpl(QuestDatabase database) {
        this.database = database;
    }

    @Override
    public void addUserTasks(List<UserTask> userTasks) {
        database.getUserQuestDao().insertAllUserTasks(userTasks);
    }

    @Override
    public void updateUserTask(UserTask userTask) {
        database.getUserQuestDao().updateUserTask(userTask);
    }

    @Override
    public List<UserTask> getAllTasksByPatternId(int patternId) {
        return database.getUserQuestDao().getAllStatusesFromPatternId(patternId);
    }

    @Override
    public List<QuestPattern> getUnusedDailyQuestPatterns() {
        return database.getQuestDao().getAllNotUsedQuests();
    }

    @Override
    public List<QuestPattern> getRepeatableDailyQuestPatterns() {
        return database.getQuestDao().getAllNotCurrentCompletedQuests();
    }
}

package com.example.ama.questapp.data.repo.provider;

import com.example.ama.questapp.data.db.QuestDatabase;
import com.example.ama.questapp.data.db.model.GlobalStatus;
import com.example.ama.questapp.data.db.model.QuestPattern;
import com.example.ama.questapp.data.db.model.UserQuest;

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
    public void addQuestsWithGlobalStatuses(List<UserQuest> userQuests, List<GlobalStatus> globalStatuses) {
        database.getUserQuestDao().insertAllQuestStatuses(userQuests);
        database.getQuestGlobalStatusDao().insertAllGlobalStatuses(globalStatuses);
    }

    @Override
    public void updateQuestWithGlobalStatus(UserQuest userQuest, GlobalStatus globalStatus) {
        database.getUserQuestDao().updateStatus(userQuest);
        database.getQuestGlobalStatusDao().updateGlobalStatus(globalStatus);
    }

    @Override
    public List<UserQuest> getAllStatusesFromPatternId(int patternId) {
        return database.getUserQuestDao().getAllStatusesFromPatternId(patternId);
    }

    @Override
    public GlobalStatus getGlobalStatusByPatternId(int patternId) {
        return database.getQuestGlobalStatusDao()
                .getGlobalStatusByPatternId(patternId);
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
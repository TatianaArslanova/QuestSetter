package com.example.ama.questapp.engine.operations.provider;

import com.example.ama.questapp.repo.db.QuestDatabase;
import com.example.ama.questapp.repo.model.GlobalStatus;
import com.example.ama.questapp.repo.model.QuestPattern;
import com.example.ama.questapp.repo.model.UserQuest;

import java.util.List;

public class EngineQuestProviderImpl implements EngineQuestProvider {
    private QuestDatabase database;

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

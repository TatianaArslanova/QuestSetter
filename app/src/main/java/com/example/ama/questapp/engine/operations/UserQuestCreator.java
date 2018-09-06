package com.example.ama.questapp.engine.operations;

import com.example.ama.questapp.repo.db.QuestDatabase;
import com.example.ama.questapp.repo.model.GlobalStatus;
import com.example.ama.questapp.repo.model.QuestPattern;
import com.example.ama.questapp.repo.model.UserQuest;

import java.util.ArrayList;
import java.util.List;

public class UserQuestCreator {
    private static final int AUTO_ID = 0;
    private static final int START_PROGRESS = 0;

    private static final int ONCE_DEFAULT_TARGET = 1;
    private static final int COUNT_DEFAULT_TARGET = 3;

    private QuestDatabase database;

    public UserQuestCreator(QuestDatabase database) {
        this.database = database;
    }

    public void createUserQuests(final List<QuestPattern> patterns) {
        List<UserQuest> userQuests = patternsToUserQuests(patterns);
        addQuestsWithGlobalStatusesToDatabase(
                userQuests,
                getGlobalStatuses(userQuests));
    }

    private int calcTargetCount(QuestPattern questPattern) {
        switch (questPattern.getQuestType()) {
            case ONCE:
                return ONCE_DEFAULT_TARGET;
            case COUNT:
                return COUNT_DEFAULT_TARGET;
            default:
                throw new IllegalArgumentException("QuestType not supported: "
                        + questPattern.getQuestType().getType());
        }
    }

    private List<UserQuest> patternsToUserQuests(List<QuestPattern> patterns) {
        List<UserQuest> questList = new ArrayList<>();
        for (QuestPattern o : patterns) {
            questList.add(createUserQuest(o));
        }
        return questList;
    }

    private List<GlobalStatus> getGlobalStatuses(List<UserQuest> userQuests) {
        List<GlobalStatus> globalStatuses = new ArrayList<>();
        for (UserQuest o : userQuests) {
            globalStatuses.add(getGlobalStatus(o));
        }
        return globalStatuses;
    }

    private GlobalStatus getGlobalStatus(UserQuest userQuest) {
        GlobalStatus globalStatus = database.getQuestGlobalStatusDao()
                .getGlobalStatusByPatternId(userQuest.getPatternId());
        if (globalStatus == null) {
            globalStatus = createGlobalStatus(userQuest);
        }
        return globalStatus;
    }

    private void addQuestsWithGlobalStatusesToDatabase(List<UserQuest> userQuests,
                                                       List<GlobalStatus> globalStatuses) {
        database.getUserQuestDao().insertAllQuestStatuses(userQuests);
        database.getQuestGlobalStatusDao().insertAllGlobalStatuses(globalStatuses);
    }

    private UserQuest createUserQuest(QuestPattern pattern) {
        return new UserQuest(AUTO_ID,
                pattern.getQuestId(),
                false,
                calcTargetCount(pattern),
                START_PROGRESS);
    }

    private GlobalStatus createGlobalStatus(UserQuest userQuest) {
        return new GlobalStatus(
                AUTO_ID,
                userQuest.getPatternId(),
                START_PROGRESS);
    }
}

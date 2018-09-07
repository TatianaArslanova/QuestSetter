package com.example.ama.questapp.domain.engine.operations;

import com.example.ama.questapp.data.repo.provider.EngineQuestProvider;
import com.example.ama.questapp.data.db.model.GlobalStatus;
import com.example.ama.questapp.data.db.model.QuestPattern;
import com.example.ama.questapp.data.db.model.UserQuest;

import java.util.ArrayList;
import java.util.List;

/***
 * Class for creating user quests from quest patterns. It does not provide using from UI-thread.
 * You should start another thread to call any method from this class.
 */

public class UserQuestCreator {
    private static final int AUTO_ID = 0;
    private static final int START_PROGRESS = 0;

    private static final int ONCE_DEFAULT_TARGET = 1;
    private static final int COUNT_DEFAULT_TARGET = 3;

    private EngineQuestProvider questProvider;

    public UserQuestCreator(EngineQuestProvider questProvider) {
        this.questProvider = questProvider;
    }

    /***
     * Creates user quests from quest patterns with updating them global status
     * or creating if it is not exists. User Quests will be added to the database automatically.
     * It mustn't be called from UI-thread
     * @param patterns list of quest patterns to create user quests
     * @see QuestPattern
     * @see UserQuest
     * @see GlobalStatus
     */

    public void createUserQuests(List<QuestPattern> patterns) {
        List<UserQuest> userQuests = patternsToUserQuests(patterns);
        questProvider.addQuestsWithGlobalStatuses(
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
        GlobalStatus globalStatus =
                questProvider.getGlobalStatusByPatternId(userQuest.getPatternId());
        if (globalStatus == null) {
            globalStatus = createGlobalStatus(userQuest);
        }
        return globalStatus;
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

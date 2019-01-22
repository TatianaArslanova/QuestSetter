package com.example.ama.questapp.domain.engine.operations;

import com.example.ama.questapp.data.db.model.QuestPattern;
import com.example.ama.questapp.data.db.model.UserTask;
import com.example.ama.questapp.data.repo.provider.EngineQuestProvider;

import java.util.ArrayList;
import java.util.List;

/***
 * Class for creating user quests from quest patterns. It does not provide using from UI-thread.
 * You should start another thread to call any method from this class.
 */

public class UserTaskCreator {
    private static final int AUTO_ID = 0;
    private static final int START_PROGRESS = 0;

    private static final int ONCE_DEFAULT_TARGET = 1;
    private static final int COUNT_DEFAULT_TARGET = 3;

    private EngineQuestProvider questProvider;

    public UserTaskCreator(EngineQuestProvider questProvider) {
        this.questProvider = questProvider;
    }

    /***
     * Creates user quests from quest patterns with updating them global status
     * or creating if it is not exists. User Quests will be added to the database automatically.
     * It mustn't be called from UI-thread
     * @param patterns list of quest patterns to create user quests
     * @see QuestPattern
     * @see UserTask
     */

    public void createUserQuests(List<QuestPattern> patterns) {
        List<UserTask> userQuests = patternsToUserQuests(patterns);
        questProvider.addUserTasks(userQuests);
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

    private List<UserTask> patternsToUserQuests(List<QuestPattern> patterns) {
        List<UserTask> questList = new ArrayList<>();
        for (QuestPattern o : patterns) {
            questList.add(createUserQuest(o));
        }
        return questList;
    }

    private UserTask createUserQuest(QuestPattern pattern) {
        return new UserTask(AUTO_ID,
                pattern.getQuestId(),
                false,
                calcTargetCount(pattern),
                START_PROGRESS);
    }
}

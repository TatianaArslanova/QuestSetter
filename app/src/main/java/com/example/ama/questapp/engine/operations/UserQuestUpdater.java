package com.example.ama.questapp.engine.operations;

import com.example.ama.questapp.engine.operations.provider.EngineQuestProvider;
import com.example.ama.questapp.repo.model.GlobalStatus;
import com.example.ama.questapp.repo.model.UserQuest;

import java.util.List;

/***
 * Class for updating existing user quests. It does not provide using from UI-thread.
 * You should start another thread to call any method from this class.
 */

public class UserQuestUpdater {
    private static final int PROGRESS_INCREMENT = 1;
    private EngineQuestProvider questProvider;

    public UserQuestUpdater(EngineQuestProvider questProvider) {
        this.questProvider = questProvider;
    }

    /***
     * Should call when needs to increment user quest progress.
     * If target progress will be achieved, it will be marked as done automatically.
     * Global progress of user quest will be updated too.
     * New data will be placed in the database.
     * It mustn't be called from UI-thread
     * @param userQuest quest that progress will be increment
     * @see UserQuest
     */

    public void onIncrementUserQuestProgress(final UserQuest userQuest) {
        questProvider.updateQuestWithGlobalStatus(
                incrementQuestProgress(userQuest),
                calculateGlobalStatus(userQuest)
        );
    }

    private UserQuest incrementQuestProgress(UserQuest userQuest) {
        userQuest.setTaskProgress(
                userQuest.getTaskProgress() + PROGRESS_INCREMENT
        );
        if (userQuest.getTaskTarget() == userQuest.getTaskProgress()) {
            userQuest.setCompleted(true);
        }
        return userQuest;
    }

    private GlobalStatus calculateGlobalStatus(UserQuest userQuest) {
        GlobalStatus globalStatus =
                questProvider.getGlobalStatusByPatternId(userQuest.getPatternId());
        globalStatus.setCountSum(calculateGlobalProgressSum(userQuest));
        return globalStatus;
    }

    private int calculateGlobalProgressSum(UserQuest userQuest) {
        List<UserQuest> questList =
                questProvider.getAllStatusesFromPatternId(userQuest.getPatternId());
        int sum = 0;
        for (UserQuest o : questList) {
            sum = sum + o.getTaskProgress();
        }
        return sum;
    }
}

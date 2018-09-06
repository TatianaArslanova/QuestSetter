package com.example.ama.questapp.engine.operations;

import com.example.ama.questapp.engine.operations.provider.EngineQuestProvider;
import com.example.ama.questapp.repo.model.GlobalStatus;
import com.example.ama.questapp.repo.model.UserQuest;

import java.util.List;

public class UserQuestUpdater {
    private static final int PROGRESS_INCREMENT = 1;
    private EngineQuestProvider questProvider;

    public UserQuestUpdater(EngineQuestProvider questProvider) {
        this.questProvider = questProvider;
    }

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

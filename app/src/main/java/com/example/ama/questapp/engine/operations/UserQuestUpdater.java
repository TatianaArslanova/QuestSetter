package com.example.ama.questapp.engine.operations;

import com.example.ama.questapp.repo.db.QuestDatabase;
import com.example.ama.questapp.repo.model.GlobalStatus;
import com.example.ama.questapp.repo.model.UserQuest;

import java.util.List;

public class UserQuestUpdater {
    private static final int PROGRESS_INCREMENT = 1;
    private QuestDatabase database;

    public UserQuestUpdater(QuestDatabase database) {
        this.database = database;
    }

    public void onIncrementUserQuestProgress(final UserQuest userQuest) {
        updateQuestWithGlobalStatus(
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
        GlobalStatus globalStatus = getGlobalStatusForUserQuest(userQuest);
        globalStatus.setCountSum(calculateGlobalProgressSum(userQuest));
        return globalStatus;
    }

    private GlobalStatus getGlobalStatusForUserQuest(UserQuest userQuest) {
        return database.getQuestGlobalStatusDao()
                .getGlobalStatusByPatternId(userQuest.getPatternId());
    }

    private int calculateGlobalProgressSum(UserQuest userQuest) {
        List<UserQuest> questList =
                database.getUserQuestDao().getAllStatusesFromPatternId(userQuest.getPatternId());
        int sum = 0;
        for (UserQuest o : questList) {
            sum = sum + o.getTaskProgress();
        }
        return sum;
    }

    private void updateQuestWithGlobalStatus(UserQuest userQuest, GlobalStatus globalStatus) {
        database.getUserQuestDao().updateStatus(userQuest);
        database.getQuestGlobalStatusDao().updateGlobalStatus(globalStatus);
    }
}

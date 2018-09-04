package com.example.ama.questapp.repo.engine;

import com.example.ama.questapp.repo.model.UserQuest;

public class UserQuestEngine {

    public UserQuest completeQuest(UserQuest userQuest) {
        userQuest.setTaskProgress(userQuest.getTaskProgress() + 1);
        if (userQuest.getTaskTarget() == userQuest.getTaskProgress()) {
            userQuest.setCompleted(true);
        }
        return userQuest;
    }
}

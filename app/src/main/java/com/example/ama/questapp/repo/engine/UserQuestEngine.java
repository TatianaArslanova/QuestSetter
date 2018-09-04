package com.example.ama.questapp.repo.engine;

import com.example.ama.questapp.repo.model.UserQuest;

public class UserQuestEngine {

    public UserQuest completeQuest(UserQuest userQuest) {
        userQuest.setCompleted(true);
        return userQuest;
    }
}

package com.example.ama.questapp.repo.engine;

import com.example.ama.questapp.repo.engine.producer.DailyUserTaskProducer;
import com.example.ama.questapp.repo.model.UserQuest;

public class UserQuestEngine {
    private DailyUserTaskProducer producer;

    public UserQuestEngine(DailyUserTaskProducer producer) {
        this.producer = producer;
    }

    public UserQuest completeQuest(UserQuest userQuest) {
        userQuest.setTaskProgress(userQuest.getTaskProgress() + 1);
        if (userQuest.getTaskTarget() == userQuest.getTaskProgress()) {
            userQuest.setCompleted(true);
        }
        return userQuest;
    }
}

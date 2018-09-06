package com.example.ama.questapp.engine;

import com.example.ama.questapp.engine.operations.UserQuestCreator;
import com.example.ama.questapp.engine.operations.UserQuestUpdater;
import com.example.ama.questapp.engine.producer.DailyUserTaskProducer;
import com.example.ama.questapp.repo.model.UserQuest;

public class UserQuestEngine {
    private static final int DEFAULT_USER_QUEST_PRODUCE_COUNT = 3;

    private DailyUserTaskProducer producer;
    private UserQuestCreator creator;
    private UserQuestUpdater updater;

    public UserQuestEngine(DailyUserTaskProducer producer, UserQuestCreator creator, UserQuestUpdater updater) {
        this.producer = producer;
        this.creator = creator;
        this.updater = updater;
    }

    public void incrementQuestProgress(UserQuest userQuest) {
        updater.onIncrementUserQuestProgress(userQuest);
    }

    public void addNewRandomDailyQuests() {
        creator.createUserQuests(
                producer.produceUserQuests(DEFAULT_USER_QUEST_PRODUCE_COUNT)
        );
    }
}

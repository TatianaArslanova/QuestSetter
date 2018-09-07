package com.example.ama.questapp.domain.engine;

import com.example.ama.questapp.domain.engine.operations.UserQuestCreator;
import com.example.ama.questapp.domain.engine.operations.UserQuestUpdater;
import com.example.ama.questapp.domain.engine.producer.DailyUserTaskProducer;
import com.example.ama.questapp.data.db.model.UserQuest;

/***
 * Class with logic of managing quests. It does not provide using from UI-thread.
 * You should start another thread to call any method from this class.
 */

public class UserQuestEngine {
    private DailyUserTaskProducer producer;
    private UserQuestCreator creator;
    private UserQuestUpdater updater;

    public UserQuestEngine(DailyUserTaskProducer producer, UserQuestCreator creator, UserQuestUpdater updater) {
        this.producer = producer;
        this.creator = creator;
        this.updater = updater;
    }

    /***
     * Delegates updating quest progress to {@link UserQuestUpdater}
     * It must'nt be called from UI-thread
     * @param userQuest quest to update progress
     * @see UserQuestUpdater
     */

    public void incrementQuestProgress(UserQuest userQuest) {
        updater.onIncrementUserQuestProgress(userQuest);
    }

    /***
     * Delegates choosing quest patterns to {@link DailyUserTaskProducer}
     * and creating new quests to {@link UserQuestCreator}
     * It must'nt be called from UI-thread
     * @param maxCount max count user quest to create.
     *                 Final amount of new quests can be less,
     *                 but never more this value
     * @see DailyUserTaskProducer
     * @see UserQuestCreator
     */

    public void addNewRandomDailyQuests(int maxCount) {
        creator.createUserQuests(
                producer.produceUserQuests(maxCount)
        );
    }
}

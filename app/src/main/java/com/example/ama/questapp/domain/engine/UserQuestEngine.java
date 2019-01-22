package com.example.ama.questapp.domain.engine;

import com.example.ama.questapp.data.db.model.UserTask;
import com.example.ama.questapp.domain.engine.operations.UserTaskCreator;
import com.example.ama.questapp.domain.engine.operations.UserTaskUpdater;
import com.example.ama.questapp.domain.engine.producer.DailyUserTaskProducer;

/***
 * Class with logic of managing quests. It does not provide using from UI-thread.
 * You should start another thread to call any method from this class.
 */

public class UserQuestEngine {
    private DailyUserTaskProducer producer;
    private UserTaskCreator creator;
    private UserTaskUpdater updater;

    public UserQuestEngine(DailyUserTaskProducer producer, UserTaskCreator creator, UserTaskUpdater updater) {
        this.producer = producer;
        this.creator = creator;
        this.updater = updater;
    }

    /***
     * Delegates updating quest progress to {@link UserTaskUpdater}
     * It must'nt be called from UI-thread
     * @param userTask quest to update progress
     * @see UserTaskUpdater
     */

    public void incrementQuestProgress(UserTask userTask) {
        updater.onIncrementUserQuestProgress(userTask);
    }

    /***
     * Delegates choosing quest patterns to {@link DailyUserTaskProducer}
     * and creating new quests to {@link UserTaskCreator}
     * It must'nt be called from UI-thread
     * @param maxCount max count user quest to create.
     *                 Final amount of new quests can be less,
     *                 but never more this value
     * @see DailyUserTaskProducer
     * @see UserTaskCreator
     */

    public void addNewRandomDailyQuests(int maxCount) {
        creator.createUserQuests(
                producer.produceUserQuests(maxCount)
        );
    }
}

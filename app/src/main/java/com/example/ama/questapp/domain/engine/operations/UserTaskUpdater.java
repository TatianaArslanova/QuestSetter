package com.example.ama.questapp.domain.engine.operations;

import com.example.ama.questapp.data.db.model.UserTask;
import com.example.ama.questapp.data.repo.provider.EngineQuestProvider;

/***
 * Class for updating existing user quests. It does not provide using from UI-thread.
 * You should start another thread to call any method from this class.
 */

public class UserTaskUpdater {
    private static final int PROGRESS_INCREMENT = 1;
    private EngineQuestProvider questProvider;

    public UserTaskUpdater(EngineQuestProvider questProvider) {
        this.questProvider = questProvider;
    }

    /***
     * Should call when needs to increment user quest progress.
     * If target progress will be achieved, it will be marked as done automatically.
     * Global progress of user quest will be updated too.
     * New data will be placed in the database.
     * It mustn't be called from UI-thread
     * @param userTask quest that progress will be increment
     * @see UserTask
     */

    public void onIncrementUserQuestProgress(final UserTask userTask) {
        questProvider.updateUserTask(incrementQuestProgress(userTask));
    }

    private UserTask incrementQuestProgress(UserTask userTask) {
        userTask.setTaskProgress(
                userTask.getTaskProgress() + PROGRESS_INCREMENT
        );
        if (userTask.getTaskTarget() == userTask.getTaskProgress()) {
            userTask.setCompleted(true);
        }
        return userTask;
    }
}

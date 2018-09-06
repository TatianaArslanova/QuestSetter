package com.example.ama.questapp.engine;

import com.example.ama.questapp.repo.db.QuestDatabase;
import com.example.ama.questapp.repo.model.UserQuest;
import com.example.ama.questapp.repo.model.pojo.PatternWithStatus;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class DatabaseOperator {
    private QuestDatabase database;

    public DatabaseOperator(QuestDatabase database) {
        this.database = database;
    }

    public Flowable<List<PatternWithStatus>> loadAllUserQuests() {
        return database.getPatternWithStatusesDao().getAllUserQuests();
    }

    public void updateUserQuest(final UserQuest userQuest) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                database.getUserQuestDao().updateStatus(userQuest);
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void addUserQuests(final List<UserQuest> quests) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                database.getUserQuestDao().insertAllQuestStatuses(quests);
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}

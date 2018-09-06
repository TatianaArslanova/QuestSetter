package com.example.ama.questapp.repo.provider;

import com.example.ama.questapp.repo.db.QuestDatabase;
import com.example.ama.questapp.repo.model.pojo.PatternWithStatus;

import java.util.List;

import io.reactivex.Flowable;

/***
 * Class for access database from {@link com.example.ama.questapp.ui.list.interactor.QuestMainListInteractorImpl}
 */

public class QuestDatabaseProvider implements QuestProvider {
    private QuestDatabase database;

    public QuestDatabaseProvider(QuestDatabase database) {
        this.database = database;
    }

    @Override
    public Flowable<List<PatternWithStatus>> loadAllUserQuests() {
        return database.getPatternWithStatusesDao().getAllUserQuests();
    }
}

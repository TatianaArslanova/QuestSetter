package com.example.ama.questapp.data.repo.provider;

import com.example.ama.questapp.data.db.QuestDatabase;
import com.example.ama.questapp.data.db.model.pojo.PatternWithStatus;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

/***
 * Class for access database from {@link com.example.ama.questapp.domain.interactor.QuestMainListInteractorImpl}
 */

@Singleton
public class QuestDatabaseProvider implements QuestProvider {
    private QuestDatabase database;

    @Inject
    public QuestDatabaseProvider(QuestDatabase database) {
        this.database = database;
    }

    @Override
    public Flowable<List<PatternWithStatus>> loadAllUserQuests() {
        return database.getPatternWithStatusesDao().getAllUserQuests();
    }
}

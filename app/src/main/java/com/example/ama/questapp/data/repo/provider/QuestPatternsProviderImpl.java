package com.example.ama.questapp.data.repo.provider;

import com.example.ama.questapp.data.db.dao.QuestPatternDao;
import com.example.ama.questapp.data.db.model.QuestPattern;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class QuestPatternsProviderImpl implements QuestPatternsProvider {
    private QuestPatternDao questPatternDao;

    @Inject
    public QuestPatternsProviderImpl(QuestPatternDao questPatternDao) {
        this.questPatternDao = questPatternDao;
    }

    @Override
    public Single<List<QuestPattern>> getUnusedDailyQuestPatterns() {
        return questPatternDao.getAllUnusedQuests()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<List<QuestPattern>> getRepeatableDailyQuestPatterns() {
        return questPatternDao.getAllNotCurrentCompletedQuests()
                .subscribeOn(Schedulers.io());
    }
}

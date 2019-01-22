package com.example.ama.questapp.data.repo.provider;

import com.example.ama.questapp.data.db.dao.QuestPatternDao;
import com.example.ama.questapp.data.db.model.QuestPattern;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class QuestPatternsProviderImpl implements QuestPatternsProvider {
    private QuestPatternDao questPatternDao;

    @Inject
    public QuestPatternsProviderImpl(QuestPatternDao questPatternDao) {
        this.questPatternDao = questPatternDao;
    }

    @Override
    public List<QuestPattern> getUnusedDailyQuestPatterns() {
        return questPatternDao.getAllNotUsedQuests();
    }

    @Override
    public List<QuestPattern> getRepeatableDailyQuestPatterns() {
        return questPatternDao.getAllNotCurrentCompletedQuests();
    }
}

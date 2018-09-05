package com.example.ama.questapp.repo.engine.producer;

import com.example.ama.questapp.repo.db.QuestDatabase;
import com.example.ama.questapp.repo.model.QuestPattern;
import com.example.ama.questapp.repo.model.UserQuest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import io.reactivex.Completable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class DailyUserTaskProducer {
    private static final int AUTO_ID = 0;
    private static final int START_PROGRESS = 0;

    private static final int ONCE_DEFAULT_TARGET = 1;
    private static final int COUNT_DEFAULT_TARGET = 3;

    private QuestDatabase database;

    public DailyUserTaskProducer(QuestDatabase database) {
        this.database = database;
    }

    public void produceUserQuests(final int maxCount) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                database.getUserQuestDao().insertAllQuestStatuses(
                        patternsToUserQuests(getPatternList(maxCount))
                );
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    private List<UserQuest> patternsToUserQuests(List<QuestPattern> patterns) {
        List<UserQuest> questList = new ArrayList<>();
        for (QuestPattern o : patterns) {
            questList.add(createUserQuest(o));
        }
        return questList;
    }

    private List<QuestPattern> getPatternList(int maxCount) {
        List<QuestPattern> patternList = new ArrayList<>(
                choosePatterns(
                        getUnusedDailyQuestPatterns(),
                        maxCount));
        if (patternList.size() < maxCount) {
            patternList.addAll(
                    choosePatterns(
                            getRepeatableDailyQuestPattern(),
                            maxCount - patternList.size()
                    ));
        }
        return patternList;
    }

    private Set<QuestPattern> choosePatterns(List<QuestPattern> patternList, int maxCount) {
        Set<QuestPattern> patternSet = new HashSet<>();
        Random random = new Random();
        if (patternList.size() < maxCount) {
            patternSet.addAll(patternList);
        } else {
            while (patternSet.size() < maxCount) {
                patternSet.add(
                        patternList.get(
                                random.nextInt(patternList.size() - 1))
                );
            }
        }
        return patternSet;
    }

    private UserQuest createUserQuest(QuestPattern pattern) {
        return new UserQuest(AUTO_ID,
                pattern.getQuestId(),
                false,
                calcTargetCount(pattern),
                START_PROGRESS);
    }

    private List<QuestPattern> getUnusedDailyQuestPatterns() {
        return database.getQuestDao().getAllNotUsedQuests();
    }

    private List<QuestPattern> getRepeatableDailyQuestPattern() {
        return database.getQuestDao().getAllNotCurrentCompletedQuests();
    }

    private int calcTargetCount(QuestPattern questPattern) {
        switch (questPattern.getQuestType()) {
            case ONCE:
                return ONCE_DEFAULT_TARGET;
            case COUNT:
                return COUNT_DEFAULT_TARGET;
            default:
                throw new IllegalArgumentException("QuestType not supported: "
                        + questPattern.getQuestType().getType());
        }
    }
}

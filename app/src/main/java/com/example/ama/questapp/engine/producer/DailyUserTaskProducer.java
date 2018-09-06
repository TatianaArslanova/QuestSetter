package com.example.ama.questapp.engine.producer;

import com.example.ama.questapp.engine.operations.provider.EngineQuestProvider;
import com.example.ama.questapp.repo.model.QuestPattern;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class DailyUserTaskProducer {

    private EngineQuestProvider questProvider;

    public DailyUserTaskProducer(EngineQuestProvider questProvider) {
        this.questProvider = questProvider;
    }

    public List<QuestPattern> produceUserQuests(final int maxCount) {
        return getPatternList(maxCount);
    }

    private List<QuestPattern> getPatternList(int maxCount) {
        List<QuestPattern> patternList = new ArrayList<>(
                choosePatterns(
                        questProvider.getUnusedDailyQuestPatterns(),
                        maxCount));
        if (patternList.size() < maxCount) {
            patternList.addAll(
                    choosePatterns(
                            questProvider.getRepeatableDailyQuestPatterns(),
                            maxCount - patternList.size()
                    ));
        }
        return patternList;
    }

    private Set<QuestPattern> choosePatterns(List<QuestPattern> patternList, int maxCount) {
        Set<QuestPattern> patternSet = new HashSet<>();
        Random random = new Random();
        if (patternList.size() <= maxCount) {
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
}

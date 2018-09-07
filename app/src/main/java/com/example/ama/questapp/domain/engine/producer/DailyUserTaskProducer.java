package com.example.ama.questapp.domain.engine.producer;

import com.example.ama.questapp.data.repo.provider.EngineQuestProvider;
import com.example.ama.questapp.data.db.model.QuestPattern;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/***
 * Class for choosing quest patterns for new daily quest creation.
 * It does not provide using from UI-thread.
 * You should start another thread to call any method from this class.
 */

public class DailyUserTaskProducer {

    private EngineQuestProvider questProvider;

    public DailyUserTaskProducer(EngineQuestProvider questProvider) {
        this.questProvider = questProvider;
    }

    /***
     * Choose {@link QuestPattern} values from database,
     * that not used yet or was completed before.
     * It mustn't be called from UI-thread
     * @param maxCount max count user quest to create.
     *                 Final amount of new quests can be less,
     *                 but never more this value
     * @return list of quest patterns free to creating new user quests
     * @see com.example.ama.questapp.data.db.model.UserQuest
     * @see QuestPattern
     */

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

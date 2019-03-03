package com.example.ama.questapp.domain.engine;

import com.example.ama.questapp.data.db.model.QuestPattern;
import com.example.ama.questapp.data.db.model.UserTask;
import com.example.ama.questapp.domain.repointerface.QuestPatternsRepository;
import com.example.ama.questapp.domain.repointerface.UserTaskRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class QuestEngine implements QuestEngineGateway {
    private static final int PROGRESS_INCREMENT = 1;
    private static final int AUTO_ID = 0;
    private static final int START_PROGRESS = 0;

    private static final int ONCE_DEFAULT_TARGET = 1;
    private static final int COUNT_DEFAULT_TARGET = 3;

    private Random random = new Random();

    private QuestPatternsRepository questPatternsRepository;
    private UserTaskRepository userTaskRepository;

    @Inject
    public QuestEngine(QuestPatternsRepository questPatternsRepository, UserTaskRepository userTaskRepository) {
        this.questPatternsRepository = questPatternsRepository;
        this.userTaskRepository = userTaskRepository;
    }

    @Override
    public Completable onIncrementQuestProgress(UserTask userTask) {
        return userTaskRepository.updateUserTask(incrementProgress(userTask));
    }

    @Override
    public Completable addNewRandomDailyQuests(int maxCount) {
        return getQuestPatternsForUserTasks(maxCount)
                .flatMapObservable(Observable::fromIterable)
                .map(this::createUserTaskFromPattern)
                .buffer(maxCount)
                .flatMapCompletable(userTasks -> userTaskRepository.addUserTasks(userTasks));
    }

    private UserTask incrementProgress(UserTask userTask) {
        userTask.setTaskProgress(
                userTask.getTaskProgress() + PROGRESS_INCREMENT
        );
        if (userTask.getTaskTarget() == userTask.getTaskProgress()) {
            userTask.setCompleted(true);
        }
        return userTask;
    }

    private Single<List<QuestPattern>> getQuestPatternsForUserTasks(int maxCount) {
        return questPatternsRepository.getUnusedDailyQuestPatterns()
                .map(questPatterns -> selectPatterns(questPatterns, maxCount))
                .flatMap(questPatterns ->
                        questPatterns.size() == maxCount ?
                                Single.just(questPatterns) : addRepeatableQuestPatterns(questPatterns, maxCount));
    }

    private Single<List<QuestPattern>> addRepeatableQuestPatterns(List<QuestPattern> selectedPatterns, int maxCount) {
        return questPatternsRepository.getRepeatableDailyQuestPatterns()
                .map(questPatterns -> selectPatterns(questPatterns, maxCount - selectedPatterns.size()))
                .map(questPatterns -> {
                    selectedPatterns.addAll(questPatterns);
                    return selectedPatterns;
                });
    }

    private List<QuestPattern> selectPatterns(List<QuestPattern> patternList, int maxCount) {
        Set<QuestPattern> selectedSet = new HashSet<>();
        if (patternList.size() <= maxCount) {
            selectedSet.addAll(patternList);
        } else {
            while (selectedSet.size() < maxCount) {
                selectedSet.add(
                        patternList.get(random.nextInt(patternList.size() - 1))
                );
            }
        }
        return new ArrayList<>(selectedSet);
    }

    private UserTask createUserTaskFromPattern(QuestPattern pattern) {
        return new UserTask(AUTO_ID,
                pattern.getQuestId(),
                false,
                calcTargetCount(pattern),
                START_PROGRESS);
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

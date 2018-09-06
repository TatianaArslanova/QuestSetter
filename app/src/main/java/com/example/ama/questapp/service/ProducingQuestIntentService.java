package com.example.ama.questapp.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.ama.questapp.QuestApp;
import com.example.ama.questapp.engine.UserQuestEngine;

import javax.inject.Inject;

/***
 * IntentService to producing new random uncompleted quests.
 * Starts by {@link ProduceTimeBroadcastReceiver}
 */

public class ProducingQuestIntentService extends IntentService {

    private final static int DEFAULT_COUNT_QUEST_TO_PRODUCE = 3;
    public final static String PRODUCE_ACTION = "com.example.ama.questapp.action.producequest";
    public final static String COUNT_KEY = "count";

    @Inject
    UserQuestEngine engine;

    public ProducingQuestIntentService() {
        super(ProducingQuestIntentService.class.getSimpleName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        QuestApp.getInstance().getComponent().inject(this);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null &&
                intent.getAction() != null &&
                intent.getAction().equals(PRODUCE_ACTION)) {
            int count = intent.getExtras() != null ?
                    intent.getExtras().getInt(COUNT_KEY) :
                    DEFAULT_COUNT_QUEST_TO_PRODUCE;
            engine.addNewRandomDailyQuests(count);
        }
    }
}

package com.example.ama.questapp.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/***
 * Broadcast receiver for starting {@link ProducingQuestIntentService} that
 * creates new random uncompleted quests. Gets intents from AlarmManager system service
 */

public class ProduceTimeBroadcastReceiver extends BroadcastReceiver {
    private static final int QUEST_TO_PRODUCE = 3;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, ProducingQuestIntentService.class);
        serviceIntent.setAction(ProducingQuestIntentService.PRODUCE_ACTION);
        serviceIntent.putExtra(ProducingQuestIntentService.COUNT_KEY, QUEST_TO_PRODUCE);
        context.startService(serviceIntent);
    }
}

package com.example.ama.questapp.repo.event;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.ama.questapp.service.ProduceTimeBroadcastReceiver;

import java.util.Calendar;
import java.util.GregorianCalendar;

/***
 * Class for planning events
 */

public class EventPlanner {
    private static final int PENDING_INTENT_REQUEST_CODE = 0;
    private static final int DEFAULT_HOURS = 7;
    private static final int DEFAULT_MINUTES = 0;
    private static final int DEFAULT_SECONDS = 0;

    private Context appContext;

    public EventPlanner(Context appContext) {
        this.appContext = appContext;
    }

    /***
     * Sets daily repeatable event by AlarmManager, that sends intent to
     * {@link ProduceTimeBroadcastReceiver} at the certain time.
     */

    public void setAutoProduceDailyQuests() {
        Intent receiverIntent = new Intent(appContext, ProduceTimeBroadcastReceiver.class);

        PendingIntent pendingIntent = PendingIntent
                .getBroadcast(appContext,
                        PENDING_INTENT_REQUEST_CODE,
                        receiverIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager =
                (AlarmManager) appContext.getSystemService(Context.ALARM_SERVICE);

        if (alarmManager != null) {
            alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    getStartingTime(),
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent);
        }
    }

    private long getStartingTime() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, DEFAULT_HOURS);
        calendar.set(Calendar.MINUTE, DEFAULT_MINUTES);
        calendar.set(Calendar.SECOND, DEFAULT_SECONDS);
        return calendar.getTimeInMillis();
    }
}

package org.dan.standup.alarm;

import static android.content.Context.ALARM_SERVICE;

import android.app.AlarmManager;
import android.app.AlarmManager.AlarmClockInfo;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import org.dan.standup.GLOBAL_CODE;

public class AlarmSettings {

    private AlarmManager alarmManager;

    private Context context;
    private PendingIntent notifyPendingIntent;
    private Intent notifyIntent;

    public AlarmSettings(Context context) {
        this.context = context;
    }

    //SET ALARMMANAGER
    public void setAlarmManager(Context context) {
        notifyIntent = new Intent(context, AlarmReceiver.class);

        notifyPendingIntent = PendingIntent.getBroadcast(
                context,
                GLOBAL_CODE.NOTIFICATION_ID.id,
                notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager =(AlarmManager) context.getSystemService(ALARM_SERVICE);
    }

    //ALARM STARTS
    public void alarmStart(){

        long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
        //long triggerTime = SystemClock.elapsedRealtime() + repeatInterval;
        long triggerTime = SystemClock.elapsedRealtime();

        if(alarmManager != null) {
            alarmManager.setInexactRepeating(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    triggerTime,
                    repeatInterval,
                    notifyPendingIntent);
        }
    }

    //CANCEL ALARM
    public void cancelAlarm(){
        alarmManager.cancel(notifyPendingIntent);
    }

    //CHECK IF THE ALARM IS WORKING
    public boolean alarmCheck(){
        boolean check = (PendingIntent.getBroadcast(
                context,
                GLOBAL_CODE.NOTIFICATION_ID.id,
                notifyIntent,
                PendingIntent.FLAG_NO_CREATE) != null);
        return check;
    }

    public String getNextClock() {
        AlarmClockInfo nextAlarmClock = alarmManager.getNextAlarmClock();
        String s;
        if (nextAlarmClock != null) {
            s = String.valueOf(nextAlarmClock.getTriggerTime());
            return s;
        } else {
            return "null";
        }
    }

}

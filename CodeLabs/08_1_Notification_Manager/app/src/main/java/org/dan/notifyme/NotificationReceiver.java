package org.dan.notifyme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationReceiver extends BroadcastReceiver {

    private Context context;
    private MainActivity mainActivity;

    public NotificationReceiver(Context context, MainActivity mainActivity) {
        this.context = context;
        this.mainActivity = mainActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(mainActivity != null){
            mainActivity.updateNotification();

        }
    }
}
